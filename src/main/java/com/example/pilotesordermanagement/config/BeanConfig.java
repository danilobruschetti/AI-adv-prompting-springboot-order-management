package com.example.pilotesordermanagement.config;

import com.example.pilotesordermanagement.mapper.CustomerMapper;
import com.example.pilotesordermanagement.mapper.OrderMapper;
import com.example.pilotesordermanagement.repository.CustomerRepository;
import com.example.pilotesordermanagement.repository.OrderRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

  @Autowired private CustomerRepository customerRepository;

  @Autowired private OrderRepository orderRepository;

  @Bean
  public CustomerMapper customerMapper() {
    return Mappers.getMapper(CustomerMapper.class);
  }

  @Bean
  public OrderMapper orderMapper() {
    return Mappers.getMapper(OrderMapper.class);
  }
}
