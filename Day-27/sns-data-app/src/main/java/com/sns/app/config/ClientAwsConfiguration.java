package com.sns.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
@Profile("!local")
public class ClientAwsConfiguration {
    @Bean
    public SnsClient snsClient() {
        return SnsClient
                .builder()
                .region(Region.US_EAST_1)
                .build();
    }
}
