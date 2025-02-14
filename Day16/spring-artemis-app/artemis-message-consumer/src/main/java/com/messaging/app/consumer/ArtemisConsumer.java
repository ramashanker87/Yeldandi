package com.messaging.app.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ArtemisConsumer {
  private static final Logger logger = LoggerFactory.getLogger(ArtemisConsumer.class.getName());
  @JmsListener(destination = "${artemis.queue-out}")
  public void receiveMessage(String message) {
    logger.info("Received message: {}", message);
  }
}
