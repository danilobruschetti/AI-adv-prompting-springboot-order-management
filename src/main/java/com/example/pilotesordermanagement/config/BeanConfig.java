package com.example.pilotesordermanagement.config;

import com.example.pilotesordermanagement.mapper.CustomerMapper;
import com.example.pilotesordermanagement.mapper.OrderMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

  @Bean
  public CustomerMapper customerMapper() {
    return Mappers.getMapper(CustomerMapper.class);
  }

  @Bean
  public OrderMapper orderMapper() {
    return Mappers.getMapper(OrderMapper.class);
  }
}
