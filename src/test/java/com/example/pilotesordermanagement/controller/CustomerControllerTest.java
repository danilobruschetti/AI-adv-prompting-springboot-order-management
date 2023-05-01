package com.example.pilotesordermanagement.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.pilotesordermanagement.dto.CustomerDto;
import com.example.pilotesordermanagement.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class CustomerControllerTest {

  @InjectMocks private CustomerController customerController;

  @Mock private CustomerService customerService;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void testCreateCustomer() throws Exception {
    CustomerDto customerDto = new CustomerDto(1L, "John", "Doe", "1234567890");
    when(customerService.createCustomer(any(CustomerDto.class))).thenReturn(customerDto);

    mockMvc
        .perform(
            post("/api/V1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDto)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.firstName").value("John"))
        .andExpect(jsonPath("$.lastName").value("Doe"))
        .andExpect(jsonPath("$.telephone").value("1234567890"));
  }

  @Test
  public void testGetCustomerById() throws Exception {
    CustomerDto customerDto = new CustomerDto(1L, "John", "Doe", "1234567890");
    when(customerService.getCustomerById(1L)).thenReturn(customerDto);

    mockMvc
        .perform(get("/api/V1/customers/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").value("John"))
        .andExpect(jsonPath("$.lastName").value("Doe"))
        .andExpect(jsonPath("$.telephone").value("1234567890"));
  }

  @Test
  public void testGetAllCustomers() throws Exception {
    List<CustomerDto> customers =
        Arrays.asList(
            new CustomerDto(1L, "John", "Doe", "1234567890"),
            new CustomerDto(2L, "Jane", "Doe", "0987654321"));

    when(customerService.getAllCustomers()).thenReturn(customers);

    mockMvc
        .perform(get("/api/V1/customers"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].firstName").value("John"))
        .andExpect(jsonPath("$.[0].lastName").value("Doe"))
        .andExpect(jsonPath("$.[0].telephone").value("1234567890"))
        .andExpect(jsonPath("$.[1].firstName").value("Jane"))
        .andExpect(jsonPath("$.[1].lastName").value("Doe"))
        .andExpect(jsonPath("$.[1].telephone").value("0987654321"));
  }
}
