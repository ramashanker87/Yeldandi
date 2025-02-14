package com.sqs.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.DeleteMessageResponse;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;
import software.amazon.awssdk.utils.Pair;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Configuration
public class SqsReceiver {
  private static final Logger log = LoggerFactory.getLogger(SqsReceiver.class);
  private final SqsAsyncClient sqsAsyncClient;
  private final ObjectMapper objectMapper;

  public SqsReceiver(SqsAsyncClient sqsAsyncClient, ObjectMapper objectMapper) {
    this.sqsAsyncClient = sqsAsyncClient;
    this.objectMapper = objectMapper;

  }

  public List<Message> readFromQueue(String queueName) throws ExecutionException, InterruptedException {
    ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest
        .builder()
        .queueUrl(queueName)
        .maxNumberOfMessages(1)
        .waitTimeSeconds(20)
        .messageAttributeNames("*")
        .build();
    ReceiveMessageResponse response = sqsAsyncClient.receiveMessage(receiveMessageRequest).get();
    log.info("Message Read Response:{}", response);
    return response.messages();
  }

  public <T> Pair<T, Map<String, MessageAttributeValue>> consumeMessage(String queueName, Class<T> messageType)
      throws JsonProcessingException, ExecutionException, InterruptedException {
    List<Message> messages = readFromQueue(queueName);
    if (messages.isEmpty()) {
      return Pair.of(null, null);
    }
    Message message = messages.get(0);
    deleteFromQueue(queueName, message);
    return Pair.of(objectMapper.readValue(message.body(), messageType), message.messageAttributes());
  }

  public void deleteFromQueue(String queueName, Message message) throws ExecutionException, InterruptedException {
    DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
        .queueUrl(queueName)
        .receiptHandle(message.receiptHandle())
        .build();
    DeleteMessageResponse deleteMessageResponse = sqsAsyncClient.deleteMessage(deleteMessageRequest).get();
    log.info("Message Delete Response:{}", deleteMessageResponse);
  }
}
