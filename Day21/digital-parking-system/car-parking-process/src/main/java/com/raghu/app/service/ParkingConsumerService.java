package com.raghu.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raghu.app.module.Car;
import com.raghu.app.module.ParkingEnd;
import com.raghu.app.module.ParkingStart;
import com.raghu.app.repository.CarRepository;
import com.raghu.app.repository.ParkingEndRepository;
import com.raghu.app.repository.ParkingStartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
public class ParkingConsumerService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ParkingStartRepository parkingStartRepository;

    @Autowired
    private ParkingEndRepository parkingEndRepository;

    private static final Logger logger = LoggerFactory.getLogger(ParkingConsumerService.class);

    private final AmqpTemplate consumerAmqpTemplate;


    public ParkingConsumerService(AmqpTemplate consumerAmqpTemplate, CarRepository carRepository) {
        this.consumerAmqpTemplate = consumerAmqpTemplate;

    }

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.start.response.routingkey.name}")
    private String startResponseRoutingKeyName;

    @Value("${rabbitmq.end.response.routingkey.name}")
    private String endResponseRoutingKeyName;

    // Listen to parking-start-request Queue
    @RabbitListener(queues = "${rabbitmq.start.request.queue.name}")
    public void processStartRequest(Map<String, Object> message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Extract car object
            Car car = objectMapper.convertValue(message.get("car"), Car.class);
            String parkingNumber = (String) message.get("parkingNumber");

            if (car == null || parkingNumber == null) {
                logger.error("Invalid message received: " + message);
                return;
            }

            logger.info("Processing parking start request for car: " + car + " at parking number: " + parkingNumber);

            // Save car details
            carRepository.save(car);

            // Save parking start details
            ParkingStart start = new ParkingStart();
            start.setStartTime(LocalDateTime.now());
            start.setRegistrationNo(car.getRegistrationNumber());
            start.setStatus("PARKED");
            start.setParkingNo(parkingNumber);

            parkingStartRepository.save(start);

            // response message
            String responseMessage = "Response - Hello " + car.getOwnerName() + ", your car " +
                    car.getRegistrationNumber() + " is parked successfully at " + start.getStartTime();

            // Send response message to the response queue
            consumerAmqpTemplate.convertAndSend(exchangeName, startResponseRoutingKeyName, responseMessage);
        } catch (Exception e) {
            logger.error("Error processing parking start request: ", e);
        }
    }



    // Listen to parking-end-request Queue
    @RabbitListener(queues = "${rabbitmq.end.request.queue.name}")
    public void processEndRequest(String parkingNumber) {
        logger.info("Processing parking end request for number: " + parkingNumber);

        // first calculate end time and push into DB

        ParkingStart start = parkingStartRepository.findByParkingNo(parkingNumber);

        Car car = carRepository.findByRegistrationNumber(start.getRegistrationNo());


        //save into parking end

        ParkingEnd end = new ParkingEnd();
        end.setEndTime(LocalDateTime.now());
        end.setRegistrationNumber(start.getRegistrationNo());
        end.setStartTime(start.getStartTime());
        end.setStatus("NOT PARKED");
        end.setParkingNumber(parkingNumber);
        long time =(int)ChronoUnit.SECONDS.between(end.getStartTime(),end.getEndTime());

        int timeInMinutes = (int) Math.ceil(time / 60.0);


        end.setPrice(timeInMinutes * 2);
        parkingEndRepository.save(end);

        carRepository.delete(car);
        parkingStartRepository.delete(start);
        // Simulating processing logic
        String responseMessage = "Response - Parking ended for number: " + parkingNumber
                + ". Cost to be collected for " + timeInMinutes + " minutes is Rs " + end.getPrice();;

        // Send response message to parking-end-response queue
        consumerAmqpTemplate.convertAndSend(exchangeName, endResponseRoutingKeyName, responseMessage);
    }
}
