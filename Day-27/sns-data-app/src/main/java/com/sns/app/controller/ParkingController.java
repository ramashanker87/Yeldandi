package com.sns.app.controller;

import com.sns.app.modle.Parking;
import com.sns.app.modle.ParkingResponse;
import com.sns.app.service.SNSServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.Instant;

@RestController
public class ParkingController {
    private final SNSServices snsServices;

    public ParkingController(SNSServices snsServices) {
        this.snsServices = snsServices;
    }

    @PostMapping("/start/parking")
    public ParkingResponse startParking(@RequestBody Parking parking) {
        Instant startTime = Instant.now();
        Instant endTime = startTime.plusSeconds(3600);
        ParkingResponse parkingResponse=new ParkingResponse();
        parkingResponse.setCarRegNo(parking.getCarRegNo());
        parkingResponse.setParkingNo(parking.getParkingNo());
        parkingResponse.setStartTime(startTime.toString());
        parkingResponse.setEndTime(endTime.toString());
        parkingResponse.setParkingStatus(parking.getParkingStatus());
        return parkingResponse;
    }
}
