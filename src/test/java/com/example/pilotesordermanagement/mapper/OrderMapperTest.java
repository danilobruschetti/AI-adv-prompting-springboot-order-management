package com.example.pilotesordermanagement.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.pilotesordermanagement.dto.OrderDto;
import com.example.pilotesordermanagement.model.Customer;
import com.example.pilotesordermanagement.model.Order;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderMapperTest {

  private OrderMapper orderMapper;

  @BeforeEach
  public void setUp() {
    orderMapper = OrderMapper.INSTANCE;
  }

  @Test
  public void toOrderDto() {
    Customer customer = new Customer();
    customer.setId(1L);
    customer.setFirstName("John");
    customer.setLastName("Doe");
    customer.setTelephone("+1234567890");

    Order order = new Order();
    order.setId(1L);
    order.setCustomer(customer);
    order.setDeliveryAddress("123 Main St");
    order.setNumberOfPilotes(10);
    order.setOrderTotal(BigDecimal.valueOf(100.0));
    order.setCreatedAt(LocalDateTime.now());

    OrderDto orderDto = orderMapper.toOrderDto(order);

    assertThat(orderDto).isNotNull();
    assertThat(orderDto.getCustomerId()).isEqualTo(customer.getId());
    assertThat(orderDto.getDeliveryAddress()).isEqualTo(order.getDeliveryAddress());
    assertThat(orderDto.getNumberOfPilotes()).isEqualTo(order.getNumberOfPilotes());
  }

  @Test
  public void toOrder() {
    OrderDto orderDto = new OrderDto();
    orderDto.setCustomerId(1L);
    orderDto.setDeliveryAddress("123 Main St");
    orderDto.setNumberOfPilotes(10);

    Order order = orderMapper.toOrder(orderDto);

    assertThat(order).isNotNull();
    assertThat(order.getId()).isNull();
    assertThat(order.getOrderTotal()).isNull();
    assertThat(order.getCreatedAt()).isNull();
    assertThat(order.getDeliveryAddress()).isEqualTo(orderDto.getDeliveryAddress());
    assertThat(order.getNumberOfPilotes()).isEqualTo(orderDto.getNumberOfPilotes());
  }
}
