package com.messaging.app.controller;

import com.messaging.app.producer.ArtemisProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
  private static final Logger logger = LoggerFactory.getLogger(ProducerController.class.getName());
  private final ArtemisProducer artemisProducer;

  public ProducerController(ArtemisProducer artemisProducer) {
    this.artemisProducer = artemisProducer;
  }

  @GetMapping("/send")
  public String send(@RequestParam("msg") String message) {
    logger.info(message);
    artemisProducer.sendMessage(message);
    return message;
  }
}
