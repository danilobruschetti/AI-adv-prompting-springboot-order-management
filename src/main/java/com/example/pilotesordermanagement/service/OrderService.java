package com.example.pilotesordermanagement.service;

import com.example.pilotesordermanagement.dto.CustomerDto;
import com.example.pilotesordermanagement.dto.OrderDto;
import com.example.pilotesordermanagement.exception.OrderNotFoundException;
import com.example.pilotesordermanagement.mapper.CustomerMapper;
import com.example.pilotesordermanagement.mapper.OrderMapper;
import com.example.pilotesordermanagement.model.Order;
import com.example.pilotesordermanagement.repository.OrderRepository;
import com.example.pilotesordermanagement.validation.OrderValidation;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final CustomerService customerService;
  private final OrderMapper orderMapper;
  private final CustomerMapper customerMapper;

  private static final BigDecimal PILOTES_PRICE = new BigDecimal("1.33");

  @Transactional
  public OrderDto createOrder(OrderDto orderDto) {
    OrderValidation.validate(orderDto);
    CustomerDto customer =
        customerService.getCustomerById(orderDto.getCustomerId()); // Fetch the customer by ID
    Order order = orderMapper.toOrder(orderDto);
    order.setCustomer(customerMapper.toCustomer(customer)); // Set the fetched customer
    order.setCreatedAt(LocalDateTime.now());
    order.setOrderTotal(calculateOrderTotal(orderDto.getNumberOfPilotes()));
    order = orderRepository.save(order);
    return orderMapper.toOrderDto(order);
  }

  @Transactional
  public OrderDto updateOrder(Long id, OrderDto orderDto) {
    Order order = getOrderById(id);
    LocalDateTime currentTime = LocalDateTime.now();

    if (currentTime.isAfter(order.getCreatedAt().plusMinutes(5))) {
      throw new IllegalStateException("Order cannot be updated after 5 minutes");
    }

    order.setNumberOfPilotes(orderDto.getNumberOfPilotes());
    order.setOrderTotal(calculateOrderTotal(order.getNumberOfPilotes()));
    Order updatedOrder = orderRepository.save(order);
    return orderMapper.toOrderDto(updatedOrder);
  }

  @Transactional(readOnly = true)
  public List<OrderDto> searchOrdersByCustomerName(String query) {
    return orderRepository.findByCustomerNameContainingIgnoreCase(query).stream()
        .map(orderMapper::toOrderDto)
        .collect(Collectors.toList());
  }

  private Order getOrderById(Long id) {
    return orderRepository
        .findById(id)
        .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + id));
  }

  private BigDecimal calculateOrderTotal(Integer numberOfPilotes) {
    return PILOTES_PRICE.multiply(new BigDecimal(numberOfPilotes));
  }
}
