package com.example.crbs_booking_service;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.DYNAMODB;

@Testcontainers
public abstract class BaseIntegrationTest {

  @Container
  static LocalStackContainer localStack = new LocalStackContainer(
      DockerImageName.parse("localstack/localstack:3.4.0"))
      .withServices(LocalStackContainer.Service.DYNAMODB)
      .waitingFor(Wait.forHttp("/_localstack/health").forStatusCode(200)); // Ensures service is up

  @DynamicPropertySource
  static void overrideProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.cloud.aws.dynamodb.endpoint",
        () -> localStack.getEndpointOverride(LocalStackContainer.Service.DYNAMODB).toString());
    registry.add("spring.cloud.aws.region.static", localStack::getRegion);
  }
}
