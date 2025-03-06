package com.sns.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

import java.net.URI;

@Configuration
@Profile("local")
public class ClientLocalConfiguration {
    @Value("${aws.endpoint}")
    String awsEndpoint;
    private final AwsCredentials awsCredentials;

    public ClientLocalConfiguration(AwsCredentials awsCredentials) {
        this.awsCredentials = awsCredentials;
    }

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder()
                .endpointOverride(URI.create(awsEndpoint))
                .region(Region.EU_WEST_1)
                .credentialsProvider(StaticCredentialsProvider.create((awsCredentials)))
                .build();
    }
}
