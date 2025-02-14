package com.sqs.app.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class ClientConfiguration {
  @Value("${aws.endpoint:}")
  String awsEndpoint;
  @Value("${site:eu-west-1}")
  private String region;
  private final AwsCredentials awsCredentials;

  public ClientConfiguration(AwsCredentials awsCredentials) {
    this.awsCredentials = awsCredentials;
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }

  @Bean
  public SqsTemplate sqsTemplate() {
    return SqsTemplate.newTemplate(sqsAsyncClient());
  }

  @Bean
  public SqsAsyncClient sqsAsyncClient() {
    return SqsAsyncClient.builder()
        .endpointOverride(URI.create(awsEndpoint))
        .region(Region.of(region))
        .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
        .build();
  }
}
