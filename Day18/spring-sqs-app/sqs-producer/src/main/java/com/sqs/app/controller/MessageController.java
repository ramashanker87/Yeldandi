package com.sqs.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqs.app.module.Student;
import com.sqs.app.service.SqsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class MessageController {
  private final SqsService sqsService;
  private final ObjectMapper objectMapper;

  public MessageController(SqsService sqsService, ObjectMapper objectMapper) {
    this.sqsService = sqsService;
    this.objectMapper = objectMapper;
  }

  @PostMapping("/send")
  public String sendMessage(@RequestBody Student student, @RequestHeader("sessionid") String sessionId) throws JsonProcessingException {
    Map<String, Object> headers = new HashMap<>();
    String correlationId = UUID.randomUUID().toString();
    headers.put("sessionid", sessionId);
    headers.put("correlationId", correlationId);
    String requestMessage = objectMapper.writeValueAsString(student);
    sqsService.sendMessage(requestMessage, headers);
    return sessionId;
  }

}
