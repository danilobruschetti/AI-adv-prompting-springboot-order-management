package com.example.pilotesordermanagement.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.pilotesordermanagement.dto.OrderDto;
import com.example.pilotesordermanagement.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

  @InjectMocks private OrderController orderController;

  @Mock private OrderService orderService;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void testCreateOrder() throws Exception {
    OrderDto orderDto = new OrderDto(1L, 1L, "123 Test Street", 5, BigDecimal.valueOf(50.0));
    when(orderService.createOrder(any(OrderDto.class))).thenReturn(orderDto);

    mockMvc
        .perform(
            post("/api/V1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDto)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.customerId").value(1L))
        .andExpect(jsonPath("$.deliveryAddress").value("123 Test Street"))
        .andExpect(jsonPath("$.numberOfPilotes").value(5))
        .andExpect(jsonPath("$.orderTotal").value(50.0));
  }

  @Test
  public void testUpdateOrder() throws Exception {
    OrderDto orderDto = new OrderDto(1L, 1L, "123 Test Street", 5, BigDecimal.valueOf(50.0));
    when(orderService.updateOrder(anyLong(), any(OrderDto.class))).thenReturn(orderDto);

    mockMvc
        .perform(
            put("/api/V1/orders/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.customerId").value(1L))
        .andExpect(jsonPath("$.deliveryAddress").value("123 Test Street"))
        .andExpect(jsonPath("$.numberOfPilotes").value(5))
        .andExpect(jsonPath("$.orderTotal").value(50.0));
  }

  @Test
  public void testSearchOrdersByCustomerName() throws Exception {
    List<OrderDto> orders =
        Arrays.asList(new OrderDto(1L, 1L, "123 Test Street", 5, BigDecimal.valueOf(50.0)));
    when(orderService.searchOrdersByCustomerName(any(String.class))).thenReturn(orders);

    mockMvc
        .perform(get("/api/V1/orders/search").param("query", "John"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].id").value(1L))
        .andExpect(jsonPath("$.[0].customerId").value(1L))
        .andExpect(jsonPath("$.[0].deliveryAddress").value("123 Test Street"))
        .andExpect(jsonPath("$.[0].numberOfPilotes").value(5))
        .andExpect(jsonPath("$.[0].orderTotal").value(50.0));
  }
}
