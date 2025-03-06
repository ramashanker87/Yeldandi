package com.dynamo.app.controller;

import com.dynamo.app.modle.Parking;
import com.dynamo.app.modle.ParkingResponse;
import com.dynamo.app.modle.ParkingTable;
import com.dynamo.app.service.DynamodbService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ParkingController {
 private final DynamodbService dynamoDbService;

  public ParkingController(DynamodbService dynamoDbService) {
    this.dynamoDbService = dynamoDbService;
  }

  @PostMapping("/start/parking")
  public String startParking(@RequestBody Parking parking){
    Instant startTime = Instant.now();
    Instant endTime=startTime.plusSeconds(3600);
    dynamoDbService.storeParkingStatus(
        parking.getCarRegNo(),
        parking.getParkingNo(),
        startTime,
        endTime,
        parking.getParkingStatus());
    return "Parking Started";
  }
  @GetMapping("/parking/status")
  private List<ParkingResponse> getParkingDetail(@RequestParam("carRegNo") String carRegNo,
      @RequestParam("parkingNo") String parkingNo){
    List<ParkingResponse> parkingResponseList = new ArrayList<>();
    QueryResponse queryResponse=dynamoDbService.getParkingStatus(carRegNo, parkingNo);
    for(Map<String, AttributeValue> item:queryResponse.items()) {
      ParkingResponse parkingResponse = new ParkingResponse();
      parkingResponse.setCarRegNo(item.get(ParkingTable.FIELD_CAR_REG_NO).s());
      parkingResponse.setParkingNo(item.get(ParkingTable.FIELD_PARKING_NO).s());
      parkingResponse.setParkingStatus(item.get(ParkingTable.FIELD_PARKING_STATUS).s());
      parkingResponse.setStartTime(item.get(ParkingTable.FIELD_START_TIME).s());
      parkingResponse.setEndTime(item.get(ParkingTable.FIELD_END_TIME).s());
      parkingResponseList.add(parkingResponse);
    }
    return parkingResponseList;
  }
}
