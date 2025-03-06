package com.raghu.app.controller;

import com.raghu.app.module.Car;
import com.raghu.app.service.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking")

public class Parking {
    private static final Logger logger = LoggerFactory.getLogger(Parking.class);

    @Autowired
    private ParkingService parkingService;
    @PostMapping("/start")
    public String parkingStart(@RequestBody Car car, @RequestParam String parkingNumber) {
        logger.info("car parking start request received {}", car.toString());

        return parkingService.parkingStartService(car, parkingNumber);
    }

    @DeleteMapping("/end")
    public String parkingEnd( @RequestParam String parkingNumber) {
        logger.info("car parking end request received for parking number {}", parkingNumber);
        return parkingService.parkingEndService(parkingNumber);
    }



}
