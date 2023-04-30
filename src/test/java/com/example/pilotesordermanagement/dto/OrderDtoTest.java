package com.example.pilotesordermanagement.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderDtoTest {

  private OrderDto orderDto;

  @BeforeEach
  public void setUp() {
    orderDto = new OrderDto();
  }

  @Test
  public void setId() {
    Long id = 1L;
    orderDto.setId(id);
    assertThat(orderDto.getId()).isEqualTo(id);
  }

  @Test
  public void setCustomerId() {
    Long customerId = 1L;
    orderDto.setCustomerId(customerId);
    assertThat(orderDto.getCustomerId()).isEqualTo(customerId);
  }

  @Test
  public void setDeliveryAddress() {
    String deliveryAddress = "123 Main St";
    orderDto.setDeliveryAddress(deliveryAddress);
    assertThat(orderDto.getDeliveryAddress()).isEqualTo(deliveryAddress);
  }

  @Test
  public void setNumberOfPilotes() {
    Integer numberOfPilotes = 5;
    orderDto.setNumberOfPilotes(numberOfPilotes);
    assertThat(orderDto.getNumberOfPilotes()).isEqualTo(numberOfPilotes);
  }

  @Test
  public void setOrderTotal() {
    BigDecimal orderTotal = new BigDecimal("100.00");
    orderDto.setOrderTotal(orderTotal);
    assertThat(orderDto.getOrderTotal()).isEqualTo(orderTotal);
  }

  @Test
  public void testEqualsAndHashCode() {
    OrderDto orderDto1 = new OrderDto();
    orderDto1.setId(1L);
    orderDto1.setCustomerId(1L);
    orderDto1.setDeliveryAddress("123 Main St");
    orderDto1.setNumberOfPilotes(5);
    orderDto1.setOrderTotal(new BigDecimal("100.00"));

    OrderDto orderDto2 = new OrderDto();
    orderDto2.setId(1L);
    orderDto2.setCustomerId(1L);
    orderDto2.setDeliveryAddress("123 Main St");
    orderDto2.setNumberOfPilotes(5);
    orderDto2.setOrderTotal(new BigDecimal("100.00"));

    assertThat(orderDto1).isEqualTo(orderDto2);
    assertThat(orderDto1.hashCode()).isEqualTo(orderDto2.hashCode());
  }

  @Test
  public void testNotEqualsAndHashCode() {
    OrderDto orderDto1 = new OrderDto();
    orderDto1.setId(1L);
    orderDto1.setCustomerId(1L);
    orderDto1.setDeliveryAddress("123 Main Str");
    orderDto1.setNumberOfPilotes(5);
    orderDto1.setOrderTotal(new BigDecimal("100.00"));

    OrderDto orderDto2 = new OrderDto();
    orderDto2.setId(1L);
    orderDto2.setCustomerId(1L);
    orderDto2.setDeliveryAddress("123 Main St");
    orderDto2.setNumberOfPilotes(5);
    orderDto2.setOrderTotal(new BigDecimal("100.00"));

    assertThat(orderDto1).isNotEqualTo(orderDto2);
    assertThat(orderDto1.hashCode()).isNotEqualTo(orderDto2.hashCode());
  }

  @Test
  public void testToString() {
    orderDto.setId(1L);
    orderDto.setCustomerId(1L);
    orderDto.setDeliveryAddress("123 Main St");
    orderDto.setNumberOfPilotes(5);
    orderDto.setOrderTotal(new BigDecimal("100.00"));

    String expectedToString =
        "OrderDto(id=1, customerId=1, deliveryAddress=123 Main St, numberOfPilotes=5, orderTotal=100.00)";
    assertThat(orderDto.toString()).isEqualTo(expectedToString);
  }
}
