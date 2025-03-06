package com.sns.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;

@Configuration
@Profile("local")
public class AwsComponentConfig {

  @Bean
  public AwsCredentials componentAwsCredential(@Value("${aws.credentials.access-key:x}") String accesskey,
      @Value("${aws.credentials.secret-key:x}") String secretkey) {
    return AwsBasicCredentials.create(accesskey, secretkey);
  }
}
