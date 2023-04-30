package com.example.pilotesordermanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.example.pilotesordermanagement.dto.OrderDto;
import com.example.pilotesordermanagement.exception.CustomerValidationException;
import com.example.pilotesordermanagement.exception.OrderValidationException;
import com.example.pilotesordermanagement.mapper.OrderMapper;
import com.example.pilotesordermanagement.model.Customer;
import com.example.pilotesordermanagement.model.Order;
import com.example.pilotesordermanagement.repository.CustomerRepository;
import com.example.pilotesordermanagement.repository.OrderRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

  @InjectMocks private OrderService orderService;

  @Mock private OrderRepository orderRepository;

  @Mock private CustomerRepository customerRepository;

  @Mock private OrderMapper orderMapper;

  private Order order;
  private OrderDto orderDto;
  private Customer customer;

  @BeforeEach
  public void setUp() {
    customer = new Customer();
    customer.setId(1L);
    customer.setFirstName("John");
    customer.setLastName("Doe");
    customer.setTelephone("+1234567890");

    order = new Order();
    order.setId(1L);
    order.setCustomer(customer);
    order.setDeliveryAddress("123 Main St");
    order.setNumberOfPilotes(10);
    order.setOrderTotal(new BigDecimal("13.30"));
    order.setCreatedAt(LocalDateTime.now().minusMinutes(1));

    orderDto = new OrderDto();
    orderDto.setId(1L);
    orderDto.setCustomerId(1L);
    orderDto.setDeliveryAddress("123 Main St");
    orderDto.setNumberOfPilotes(10);
    orderDto.setOrderTotal(new BigDecimal("13.30"));
  }

  @Test
  public void testCreateOrder_success() {
    when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
    when(orderMapper.toOrder(orderDto)).thenReturn(order);
    when(orderRepository.save(order)).thenReturn(order);
    when(orderMapper.toOrderDto(order)).thenReturn(orderDto);

    OrderDto result = orderService.createOrder(orderDto);
    assertEquals(orderDto, result);
  }

  @Test
  public void testCreateOrder_throwsCustomerValidationException() {
    when(customerRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(CustomerValidationException.class, () -> orderService.createOrder(orderDto));
  }

  @Test
  public void testUpdateOrder_success() {
    Order orderToUpdate = new Order();
    orderToUpdate.setId(1L);
    orderToUpdate.setCustomer(customer);
    orderToUpdate.setDeliveryAddress("123 Main St");
    orderToUpdate.setNumberOfPilotes(10);
    orderToUpdate.setOrderTotal(new BigDecimal("13.30"));
    orderToUpdate.setCreatedAt(LocalDateTime.now().minusMinutes(1));

    when(orderRepository.findById(1L)).thenReturn(Optional.of(orderToUpdate));

    OrderDto updatedOrderDto = new OrderDto();
    updatedOrderDto.setId(1L);
    updatedOrderDto.setCustomerId(1L);
    updatedOrderDto.setDeliveryAddress("456 Main St");
    updatedOrderDto.setNumberOfPilotes(15);
    updatedOrderDto.setOrderTotal(new BigDecimal("19.95"));

    Order updatedOrder = new Order();
    updatedOrder.setId(1L);
    updatedOrder.setCustomer(customer);
    updatedOrder.setDeliveryAddress("456 Main St");
    updatedOrder.setNumberOfPilotes(15);
    updatedOrder.setOrderTotal(new BigDecimal("19.95"));
    updatedOrder.setCreatedAt(orderToUpdate.getCreatedAt());

    when(orderRepository.save(updatedOrder)).thenReturn(updatedOrder);
    when(orderMapper.toOrderDto(updatedOrder)).thenReturn(updatedOrderDto);

    OrderDto result = orderService.updateOrder(1L, updatedOrderDto);
    assertEquals(updatedOrderDto, result);
  }

  @Test
  public void testUpdateOrder_throwsOrderValidationException() {
    Order orderWithPastTime = new Order();
    orderWithPastTime.setId(1L);
    orderWithPastTime.setCustomer(customer);
    orderWithPastTime.setDeliveryAddress("123 Main St");
    orderWithPastTime.setNumberOfPilotes(10);
    orderWithPastTime.setOrderTotal(new BigDecimal("13.30"));
    orderWithPastTime.setCreatedAt(LocalDateTime.now().minusMinutes(6));

    when(orderRepository.findById(1L)).thenReturn(Optional.of(orderWithPastTime));

    assertThrows(OrderValidationException.class, () -> orderService.updateOrder(1L, orderDto));
  }

  @Test
  public void testSearchOrdersByCustomerName() {
    Order order2 = new Order();
    order2.setId(2L);
    order2.setCustomer(customer);
    order2.setDeliveryAddress("789 Main St");
    order2.setNumberOfPilotes(5);
    order2.setOrderTotal(new BigDecimal("6.65"));
    order2.setCreatedAt(LocalDateTime.now());

    OrderDto orderDto2 = new OrderDto();
    orderDto2.setId(2L);
    orderDto2.setCustomerId(1L);
    orderDto2.setDeliveryAddress("789 Main St");
    orderDto2.setNumberOfPilotes(5);
    orderDto2.setOrderTotal(new BigDecimal("6.65"));

    List<Order> orders = Arrays.asList(order, order2);
    List<OrderDto> orderDtos = Arrays.asList(orderDto, orderDto2);

    when(orderRepository.findByCustomerNameContainingIgnoreCase("Doe")).thenReturn(orders);
    when(orderMapper.toOrderDto(order)).thenReturn(orderDto);
    when(orderMapper.toOrderDto(order2)).thenReturn(orderDto2);

    List<OrderDto> result = orderService.searchOrdersByCustomerName("Doe");

    assertEquals(orderDtos, result);
  }
}
