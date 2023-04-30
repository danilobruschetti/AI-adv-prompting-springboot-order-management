package com.example.pilotesordermanagement.service;

import com.example.pilotesordermanagement.dto.OrderDto;
import com.example.pilotesordermanagement.exception.CustomerValidationException;
import com.example.pilotesordermanagement.exception.OrderNotFoundException;
import com.example.pilotesordermanagement.exception.OrderValidationException;
import com.example.pilotesordermanagement.mapper.OrderMapper;
import com.example.pilotesordermanagement.model.Customer;
import com.example.pilotesordermanagement.model.Order;
import com.example.pilotesordermanagement.repository.CustomerRepository;
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
  private final CustomerRepository customerRepository;
  private final OrderMapper orderMapper;
  private static final BigDecimal PILOTES_PRICE = new BigDecimal("1.33");

  @Transactional
  public OrderDto createOrder(OrderDto orderDto) {
    OrderValidation.validate(orderDto);
    Customer customer =
        customerRepository
            .findById(orderDto.getCustomerId())
            .orElseThrow(
                () ->
                    new CustomerValidationException(
                        "Customer not found with ID: " + orderDto.getCustomerId()));
    Order order = orderMapper.toOrder(orderDto);
    order.setCustomer(customer); // Set the fetched customer
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
      throw new OrderValidationException("Order cannot be updated after 5 minutes");
    }
    order.setDeliveryAddress(orderDto.getDeliveryAddress());
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
