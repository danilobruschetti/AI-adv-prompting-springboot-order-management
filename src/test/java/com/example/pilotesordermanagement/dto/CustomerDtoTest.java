package com.example.pilotesordermanagement.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerDtoTest {

  private CustomerDto customerDto;

  @BeforeEach
  public void setUp() {
    customerDto = new CustomerDto();
  }

  @Test
  public void setId() {
    Long id = 1L;
    customerDto.setId(id);
    assertThat(customerDto.getId()).isEqualTo(id);
  }

  @Test
  public void setFirstName() {
    String firstName = "John";
    customerDto.setFirstName(firstName);
    assertThat(customerDto.getFirstName()).isEqualTo(firstName);
  }

  @Test
  public void setLastName() {
    String lastName = "Doe";
    customerDto.setLastName(lastName);
    assertThat(customerDto.getLastName()).isEqualTo(lastName);
  }

  @Test
  public void setTelephone() {
    String telephone = "+1234567890";
    customerDto.setTelephone(telephone);
    assertThat(customerDto.getTelephone()).isEqualTo(telephone);
  }

  @Test
  public void testEqualsAndHashCode() {
    CustomerDto customerDto1 = new CustomerDto();
    customerDto1.setId(1L);
    customerDto1.setFirstName("John");
    customerDto1.setLastName("Doe");
    customerDto1.setTelephone("+1234567890");

    CustomerDto customerDto2 = new CustomerDto();
    customerDto2.setId(1L);
    customerDto2.setFirstName("John");
    customerDto2.setLastName("Doe");
    customerDto2.setTelephone("+1234567890");

    assertThat(customerDto1).isEqualTo(customerDto2);
    assertThat(customerDto1.hashCode()).isEqualTo(customerDto2.hashCode());
  }

  @Test
  public void testNotEqualsAndHashCode() {
    CustomerDto customerDto1 = new CustomerDto();
    customerDto1.setId(1L);
    customerDto1.setFirstName("Johnny");
    customerDto1.setLastName("Doe");
    customerDto1.setTelephone("+1234567890");

    CustomerDto customerDto2 = new CustomerDto();
    customerDto2.setId(1L);
    customerDto2.setFirstName("John");
    customerDto2.setLastName("Doe");
    customerDto2.setTelephone("+1234567890");

    assertThat(customerDto1).isNotEqualTo(customerDto2);
    assertThat(customerDto1.hashCode()).isNotEqualTo(customerDto2.hashCode());
  }

  @Test
  public void testToString() {
    customerDto.setId(1L);
    customerDto.setFirstName("John");
    customerDto.setLastName("Doe");
    customerDto.setTelephone("+1234567890");

    String expectedToString =
        "CustomerDto(id=1, firstName=John, lastName=Doe, telephone=+1234567890)";
    assertThat(customerDto.toString()).isEqualTo(expectedToString);
  }
}
