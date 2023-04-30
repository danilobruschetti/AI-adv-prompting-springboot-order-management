package com.example.pilotesordermanagement.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.pilotesordermanagement.dto.CustomerDto;
import com.example.pilotesordermanagement.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerMapperTest {

  private CustomerMapper customerMapper;

  @BeforeEach
  public void setUp() {
    customerMapper = CustomerMapper.INSTANCE;
  }

  @Test
  public void toCustomerDto() {
    Customer customer = new Customer();
    customer.setId(1L);
    customer.setFirstName("John");
    customer.setLastName("Doe");
    customer.setTelephone("+1234567890");

    CustomerDto customerDto = customerMapper.toCustomerDto(customer);

    assertThat(customerDto).isNotNull();
    assertThat(customerDto.getFirstName()).isEqualTo(customer.getFirstName());
    assertThat(customerDto.getLastName()).isEqualTo(customer.getLastName());
    assertThat(customerDto.getTelephone()).isEqualTo(customer.getTelephone());
  }

  @Test
  public void toCustomer() {
    CustomerDto customerDto = new CustomerDto();
    customerDto.setFirstName("John");
    customerDto.setLastName("Doe");
    customerDto.setTelephone("+1234567890");

    Customer customer = customerMapper.toCustomer(customerDto);

    assertThat(customer).isNotNull();
    assertThat(customer.getId()).isNull();
    assertThat(customer.getFirstName()).isEqualTo(customerDto.getFirstName());
    assertThat(customer.getLastName()).isEqualTo(customerDto.getLastName());
    assertThat(customer.getTelephone()).isEqualTo(customerDto.getTelephone());
  }
}
