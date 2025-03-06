package com.dynamo.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
@Profile("!local")
public class ClientAwsConfiguration {
  @Bean
  public DynamoDbClient dynamoDbClient() {
    return DynamoDbClient
        .builder()
        .region(Region.US_EAST_1)
        .build();
  }
}
