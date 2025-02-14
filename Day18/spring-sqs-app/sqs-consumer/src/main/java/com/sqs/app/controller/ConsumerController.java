package com.sqs.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sqs.app.module.Student;
import com.sqs.app.service.SqsReceiver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;
import software.amazon.awssdk.utils.Pair;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class ConsumerController {
  private final SqsReceiver sqsReceiver;
  @Value("${amazon.aws.sqs.queue}")
  private String queue;
  public ConsumerController(SqsReceiver sqsReceiver) {
    this.sqsReceiver = sqsReceiver;
  }

  @GetMapping("/receive")
  public ResponseEntity<Student> receive() throws ExecutionException, JsonProcessingException, InterruptedException {
    Pair<Student, Map<String, MessageAttributeValue>> message= sqsReceiver.consumeMessage(queue,Student.class);
    Student student= message.left();
    Map<String, MessageAttributeValue> messageAttributes = message.right();
    HttpHeaders headers = new HttpHeaders();
    messageAttributes.forEach((key,value)->{
      if(value.stringValue()!=null){
        headers.add(key,value.stringValue());
      }
    });
    return new ResponseEntity<>(student,headers, HttpStatus.ACCEPTED);

  }
}
