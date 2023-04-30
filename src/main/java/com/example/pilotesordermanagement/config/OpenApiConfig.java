package com.example.pilotesordermanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI usersMicroserviceOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Pilotes Order Management")
                .description(
                    "Pilotes Order Management is a web-based order management system built with Java 18, Spring Boot, lombok and an in-memory H2 database."
                        + " The system allows you to create and manage customer orders, fetch customer information, and calculate order totals.")
                .version("1.0"));
  }
}
