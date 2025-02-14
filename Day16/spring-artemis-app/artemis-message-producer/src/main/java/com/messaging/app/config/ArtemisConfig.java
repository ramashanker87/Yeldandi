package com.messaging.app.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;

@Configuration
public class ArtemisConfig {

  @Value("${brokerUrl}")
  private String brokerUrl;
  @Value("${artemis.user}")
  private String username;
  @Value("${artemis.password}")
  private String password;
  @Value("${artemis.connection.size}")
  private int connectionSize;

  @Bean
  public CachingConnectionFactory cachingConnectionFactory() {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username, password, brokerUrl);
    connectionFactory.setTrustAllPackages(true);
    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);
    cachingConnectionFactory.setSessionCacheSize(connectionSize);
    return cachingConnectionFactory;
  }

}
