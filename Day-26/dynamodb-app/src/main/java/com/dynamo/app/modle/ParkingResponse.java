package com.dynamo.app.modle;

import java.util.Date;

public class ParkingResponse {
  String carRegNo;
  String parkingNo;
  String parkingStatus;

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  String startTime;
 String endTime;


  public String getParkingStatus() {
    return parkingStatus;
  }

  public void setParkingStatus(String parkingStatus) {
    this.parkingStatus = parkingStatus;
  }

  public String getParkingNo() {
    return parkingNo;
  }

  public void setParkingNo(String parkingNo) {
    this.parkingNo = parkingNo;
  }

  public String getCarRegNo() {
    return carRegNo;
  }

  public void setCarRegNo(String carRegNo) {
    this.carRegNo = carRegNo;
  }


}
