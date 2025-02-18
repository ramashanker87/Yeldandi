package com.logging.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {
  private static final Logger logger = LoggerFactory.getLogger(LoggingService.class);
  public void performLogging() {
    logger.trace("TRACE Level Log");
    logger.debug("DEBUG Level Log");
    logger.info("INFO Level Log");
    logger.warn("WARN Level Log");
    logger.error("ERROR Level Log");
  }
}
