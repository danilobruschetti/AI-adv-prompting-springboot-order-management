package com.example.pilotesordermanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.example.pilotesordermanagement.dto.CustomerDto;
import com.example.pilotesordermanagement.exception.CustomerNotFoundException;
import com.example.pilotesordermanagement.mapper.CustomerMapper;
import com.example.pilotesordermanagement.model.Customer;
import com.example.pilotesordermanagement.repository.CustomerRepository;
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
public class CustomerServiceTest {

  @InjectMocks private CustomerService customerService;

  @Mock private CustomerRepository customerRepository;

  @Mock private CustomerMapper customerMapper;

  private Customer customer;
  private CustomerDto customerDto;

  @BeforeEach
  public void setUp() {
    customer = new Customer();
    customer.setId(1L);
    customer.setFirstName("John");
    customer.setLastName("Doe");
    customer.setTelephone("+1234567890");

    customerDto = new CustomerDto();
    customerDto.setId(1L);
    customerDto.setFirstName("John");
    customerDto.setLastName("Doe");
    customerDto.setTelephone("+1234567890");
  }

  @Test
  public void testGetCustomerById_success() {
    when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
    when(customerMapper.toCustomerDto(customer)).thenReturn(customerDto);

    CustomerDto result = customerService.getCustomerById(1L);
    assertEquals(customerDto, result);
  }

  @Test
  public void testGetCustomerById_throwsCustomerNotFoundException() {
    when(customerRepository.findById(1L)).thenReturn(Optional.empty());
    assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(1L));
  }

  @Test
  public void testCreateCustomer_success() {
    when(customerMapper.toCustomer(customerDto)).thenReturn(customer);
    when(customerRepository.save(customer)).thenReturn(customer);
    when(customerMapper.toCustomerDto(customer)).thenReturn(customerDto);

    CustomerDto result = customerService.createCustomer(customerDto);
    assertEquals(customerDto, result);
  }

  @Test
  public void testGetAllCustomers_success() {
    Customer customer2 = new Customer();
    customer2.setId(2L);
    customer2.setFirstName("Jane");
    customer2.setLastName("Doe");
    customer2.setTelephone("+1234567891");

    CustomerDto customerDto2 = new CustomerDto();
    customerDto2.setId(2L);
    customerDto2.setFirstName("Jane");
    customerDto2.setLastName("Doe");
    customerDto2.setTelephone("+1234567891");

    List<Customer> customers = Arrays.asList(customer, customer2);
    List<CustomerDto> customerDtos = Arrays.asList(customerDto, customerDto2);

    when(customerRepository.findAll()).thenReturn(customers);
    when(customerMapper.toCustomerDto(customer)).thenReturn(customerDto);
    when(customerMapper.toCustomerDto(customer2)).thenReturn(customerDto2);

    List<CustomerDto> result = customerService.getAllCustomers();
    assertEquals(customerDtos, result);
  }
}
