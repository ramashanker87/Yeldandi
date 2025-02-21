package com.raghu.app.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import java.time.LocalDateTime;

@DynamoDbBean
public class ParkingStart {
    private String parkingNo;
    private String regNo;
    private LocalDateTime startTime;
    private String status;

    @DynamoDbPartitionKey
    public String getParkingNo() { return parkingNo; }

    public void setParkingNo(String parkingNo) { this.parkingNo = parkingNo; }
    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}