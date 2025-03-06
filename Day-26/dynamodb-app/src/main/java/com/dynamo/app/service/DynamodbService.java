package com.dynamo.app.service;

import com.dynamo.app.exception.ParkingException;
import com.dynamo.app.modle.ParkingTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class DynamodbService {
  Logger logger = LoggerFactory.getLogger(DynamodbService.class);
  @Value("${amazon.aws.dynamodb.parking.table}")
  String parkingTable;
  private final DynamoDbClient dynamoDbClient;

  public DynamodbService(DynamoDbClient dynamoDbClient) {
    this.dynamoDbClient = dynamoDbClient;
  }

  public void storeParkingStatus(String carRegNo, String parkingNo, Instant startTime, Instant endTime, String parkingStatus) throws ParkingException {
    Map<String, AttributeValue> item = new HashMap<>();

    //Create the item to store in Dynamodb
    item.put(ParkingTable.FIELD_CAR_REG_NO, AttributeValue.builder().s(carRegNo).build());
    item.put(ParkingTable.FIELD_PARKING_NO, AttributeValue.builder().s(parkingNo).build());
    item.put(ParkingTable.FIELD_START_TIME, AttributeValue.builder().s(String.valueOf(startTime)).build());
    item.put(ParkingTable.FIELD_END_TIME, AttributeValue.builder().s(String.valueOf(endTime)).build());
    item.put(ParkingTable.FIELD_PARKING_STATUS, AttributeValue.builder().s(parkingStatus).build());
    PutItemRequest putItemRequest = PutItemRequest.builder()
        .tableName(parkingTable)
        .item(item)
        .build();
    try {
      PutItemResponse putItemResponse = dynamoDbClient.putItem(putItemRequest);
      logger.info(putItemResponse.toString());
    } catch (DynamoDbException e) {
      logger.error("Unable to add item in dynamodb table:{}", parkingTable, e);
      throw new ParkingException("Aws Dynamodb Data Store exception:" + e.getMessage());
    }
  }

  public QueryResponse getParkingStatus(String carRegNo, String parkingNo) throws ParkingException {
    logger.info("Retrieve parking status from dynamodb table:{}", parkingTable);
    try {
      Map<String, String> expressionAttribute = new HashMap<>();
      expressionAttribute.put("#carRegNo", ParkingTable.FIELD_CAR_REG_NO);
      expressionAttribute.put("#parkiNo", ParkingTable.FIELD_PARKING_NO);
      expressionAttribute.put("#startTime", ParkingTable.FIELD_START_TIME);
      expressionAttribute.put("#endTime", ParkingTable.FIELD_END_TIME);
      expressionAttribute.put("#parkingStatus", ParkingTable.FIELD_PARKING_STATUS);

      Map<String, AttributeValue> expressionAttributeValue = new HashMap<>();
      expressionAttributeValue.put(":carRegNo", AttributeValue.builder().s(carRegNo).build());
      expressionAttributeValue.put(":parkiNo", AttributeValue.builder().s(parkingNo).build());

//      QueryRequest queryRequest = QueryRequest.builder()
//          .tableName(parkingTable)
//          .keyConditionExpression("carRegNo = :carRegNo")
//          .projectionExpression("#carRegNo,#parkiNo,#startTime,#endTime,#parkingStatus")
//          .expressionAttributeNames(expressionAttribute)
//          .expressionAttributeValues(expressionAttributeValue)
//          .build();

      QueryRequest queryRequest = QueryRequest.builder()
              .tableName(parkingTable)
              .keyConditionExpression("#carRegNo = :carRegNo AND #parkiNo = :parkiNo") // Now using both attributes
              .projectionExpression("#carRegNo,#parkiNo,#startTime,#endTime,#parkingStatus")
              .expressionAttributeNames(expressionAttribute)
              .expressionAttributeValues(expressionAttributeValue)
              .build();

      return dynamoDbClient.query(queryRequest);
    }catch (DynamoDbException e) {
      logger.error("Unable to retrieve parking status from dynamodb table:{}", parkingTable, e);
      throw new ParkingException("Aws Dynamodb Data Store exception:" + e.getMessage());
    }
  }
}
