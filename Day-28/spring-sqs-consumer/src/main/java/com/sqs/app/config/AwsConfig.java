package com.sqs.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;

@Configuration
public class AwsConfig {

  @Bean
  public AwsCredentials awsCredentials(@Value("${aws.accessKey:x}") String accesskey,
                                       @Value("${aws.secretKey:x}") String secretkey) {
    return AwsBasicCredentials.create(accesskey, secretkey);
  }
}
