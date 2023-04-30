package com.example.pilotesordermanagement.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderTest {

  private Order order;

  @BeforeEach
  void setUp() {
    order = new Order();
  }

  @Test
  void setId_GetId() {
    Long id = 1L;
    order.setId(id);
    assertThat(order.getId()).isEqualTo(id);
  }

  @Test
  void setCustomer_GetCustomer() {
    Customer customer = new Customer();
    customer.setId(1L);
    order.setCustomer(customer);
    assertThat(order.getCustomer()).isEqualTo(customer);
  }

  @Test
  void setDeliveryAddress_GetDeliveryAddress() {
    String address = "123 Main St";
    order.setDeliveryAddress(address);
    assertThat(order.getDeliveryAddress()).isEqualTo(address);
  }

  @Test
  void setNumberOfPilotes_GetNumberOfPilotes() {
    Integer numberOfPilotes = 10;
    order.setNumberOfPilotes(numberOfPilotes);
    assertThat(order.getNumberOfPilotes()).isEqualTo(numberOfPilotes);
  }

  @Test
  void setOrderTotal_GetOrderTotal() {
    BigDecimal orderTotal = BigDecimal.valueOf(100.0);
    order.setOrderTotal(orderTotal);
    assertThat(order.getOrderTotal()).isEqualTo(orderTotal);
  }

  @Test
  void setCreatedAt_GetCreatedAt() {
    LocalDateTime createdAt = LocalDateTime.now();
    order.setCreatedAt(createdAt);
    assertThat(order.getCreatedAt()).isEqualTo(createdAt);
  }

  @Test
  void testEquals() {
    Order order1 = new Order();
    order1.setId(1L);
    order1.setCustomer(new Customer());
    order1.setDeliveryAddress("123 Main St");
    order1.setNumberOfPilotes(10);
    order1.setOrderTotal(BigDecimal.valueOf(100.0));
    order1.setCreatedAt(LocalDateTime.now());

    Order order2 = new Order();
    order2.setId(1L);
    order2.setCustomer(new Customer());
    order2.setDeliveryAddress("123 Main St");
    order2.setNumberOfPilotes(10);
    order2.setOrderTotal(BigDecimal.valueOf(100.0));
    order2.setCreatedAt(LocalDateTime.now());

    assertThat(order1).isEqualTo(order2);
  }

  @Test
  void testNotEquals() {
    Order order1 = new Order();
    order1.setId(1L);
    order1.setCustomer(new Customer());
    order1.setDeliveryAddress("123 Main Stet");
    order1.setNumberOfPilotes(10);
    order1.setOrderTotal(BigDecimal.valueOf(100.0));
    order1.setCreatedAt(LocalDateTime.now());

    Order order2 = new Order();
    order2.setId(1L);
    order2.setCustomer(new Customer());
    order2.setDeliveryAddress("123 Main St");
    order2.setNumberOfPilotes(10);
    order2.setOrderTotal(BigDecimal.valueOf(100.0));
    order2.setCreatedAt(LocalDateTime.now());

    assertThat(order1).isNotEqualTo(order2);
  }

  @Test
  void testHashCode() {
    Order order1 = new Order();
    order1.setId(1L);
    order1.setCustomer(new Customer());
    order1.setDeliveryAddress("123 Main St");
    order1.setNumberOfPilotes(10);
    order1.setOrderTotal(BigDecimal.valueOf(100.0));
    order1.setCreatedAt(LocalDateTime.now());

    Order order2 = new Order();
    order2.setId(1L);
    order2.setCustomer(new Customer());
    order2.setDeliveryAddress("123 Main St");
    order2.setNumberOfPilotes(10);
    order2.setOrderTotal(BigDecimal.valueOf(100.0));
    order2.setCreatedAt(LocalDateTime.now());

    assertThat(order1.hashCode()).isEqualTo(order2.hashCode());
  }

  @Test
  void testToString() {
    Order order = new Order();
    order.setId(1L);
    order.setCustomer(new Customer());
    order.setDeliveryAddress("123 Main St");
    order.setNumberOfPilotes(10);
    order.setOrderTotal(BigDecimal.valueOf(100.0));
    order.setCreatedAt(LocalDateTime.now());

    String expected =
        "Order(id=1, customer="
            + order.getCustomer()
            + ", deliveryAddress=123 Main St, numberOfPilotes=10, orderTotal=100.0, createdAt="
            + order.getCreatedAt()
            + ")";
    assertThat(order.toString()).isEqualTo(expected);
  }
}
