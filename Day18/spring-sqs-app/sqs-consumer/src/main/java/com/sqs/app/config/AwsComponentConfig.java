package com.sqs.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;

@Configuration
public class AwsComponentConfig {
  @Bean
  public AwsCredentials awsCredentials(@Value("${aws.accesskey:x}") String accessKey,@Value("${aws.secretKey:x}") String secretKey) {
    return AwsBasicCredentials.create(accessKey, secretKey);
  }
}
