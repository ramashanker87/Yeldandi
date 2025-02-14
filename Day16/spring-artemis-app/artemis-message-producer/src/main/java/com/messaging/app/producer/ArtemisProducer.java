package com.messaging.app.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ArtemisProducer {
 private static final Logger logger = LoggerFactory.getLogger(ArtemisProducer.class);
  private final JmsTemplate jmsTemplate;

  @Value("${artemis.queue-out}")
  private String queueOut;
  public ArtemisProducer(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  public void sendMessage(String message) {
    logger.info("Sending message: {}", message);
    jmsTemplate.convertAndSend(queueOut, message);
  }
}

