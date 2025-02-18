package com.logging.app.controller;

import com.logging.app.service.LoggingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
  private static final Logger logger = LoggerFactory.getLogger(LogController.class);
  private final LoggingService loggingService;

  public LogController(LoggingService loggingService) {
    this.loggingService = loggingService;
  }

  @GetMapping("/log")
  public String logData() {
    logger.debug("Receive rquest for log Data");
    loggingService.performLogging();
    return "Log Data";
  }
}
