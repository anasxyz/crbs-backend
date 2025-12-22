package com.example.crbs_location_room_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

import java.net.URI;

@Configuration
public class DynamoDbConfig {

  @Value("${spring.cloud.aws.dynamodb.endpoint:#{null}}")
  private String endpoint;

  @Value("${spring.cloud.aws.region.static:us-east-1}")
  private String region;

  @Bean
  public DynamoDbClient dynamoDbClient() {
    DynamoDbClientBuilder builder = DynamoDbClient.builder()
        .region(Region.of(region));

    // if an endpoint is provided (like in tests), override it
    if (endpoint != null && !endpoint.isEmpty()) {
      builder.endpointOverride(URI.create(endpoint))
          .credentialsProvider(StaticCredentialsProvider.create(
              AwsBasicCredentials.create("test", "test"))); // creds for ci testing
    }

    return builder.build();
  }
}
