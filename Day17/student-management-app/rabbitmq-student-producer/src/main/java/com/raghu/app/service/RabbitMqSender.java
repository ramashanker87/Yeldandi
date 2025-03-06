package com.raghu.app.service;

import com.raghu.app.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.AmqpTemplate;

@Service
public class RabbitMqSender {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqSender.class.getName());

    //added maven dependency for this
    private final AmqpTemplate amqpTemplate;
    // Constructor injection
    public RabbitMqSender(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @Value("${rabbitmq.exchange.name}")
    String exchangeName;

    @Value("${rabbitmq.routingkey.name}")
    String routingKeyName;

    @Value("${rabbitmq.student.routingkey.name}")
    String studentRoutingKeyName;

    public void sendStudent(Student student) {
        logger.info("Sending Student: {}" + student.toString());
        amqpTemplate.convertAndSend(exchangeName, studentRoutingKeyName, student);
    }

    public void deleteStudent(String name) {
        logger.info("Deleting Student: {} " + name);
        amqpTemplate.convertAndSend(exchangeName, routingKeyName, name);
    }
}
