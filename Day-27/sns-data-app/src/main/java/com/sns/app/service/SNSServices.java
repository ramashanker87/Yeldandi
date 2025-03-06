package com.sns.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sns.app.modle.Parking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Service
public class SNSServices {
    Logger logger = LoggerFactory.getLogger(SNSServices.class);
    @Value("${sns.topic.arn}")
    String snsArn;
    private final SnsClient snsClient;

    public SNSServices(SnsClient snsClient) {
        this.snsClient = snsClient;
    }

    private void sendMessageToSns(Parking parking) throws JsonProcessingException {
        String message = toJson(parking);
        PublishRequest request = PublishRequest.builder()
                .message(message)
                .topicArn(snsArn)
                .build();
        PublishResponse result = snsClient.publish(request);
        logger.info(parking.getCarRegNo(), "message sns response, message-id" + result.messageId() + " Status: " + result.sdkHttpResponse().statusCode());
    }

    private String toJson(Parking parking) throws JsonProcessingException {
        return parking == null ? null : new ObjectMapper().writeValueAsString(parking);
    }
}
