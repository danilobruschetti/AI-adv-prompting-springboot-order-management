package com.example.pilotesordermanagement.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerTest {

  private Customer customer;

  @BeforeEach
  void setUp() {
    customer = new Customer();
  }

  @Test
  void setId_GetId() {
    Long id = 1L;
    customer.setId(id);
    assertThat(customer.getId()).isEqualTo(id);
  }

  @Test
  void setFirstName_GetFirstName() {
    String firstName = "John";
    customer.setFirstName(firstName);
    assertThat(customer.getFirstName()).isEqualTo(firstName);
  }

  @Test
  void setLastName_GetLastName() {
    String lastName = "Doe";
    customer.setLastName(lastName);
    assertThat(customer.getLastName()).isEqualTo(lastName);
  }

  @Test
  void setTelephone_GetTelephone() {
    String telephone = "+1234567890";
    customer.setTelephone(telephone);
    assertThat(customer.getTelephone()).isEqualTo(telephone);
  }

  @Test
  void testEquals() {
    Customer customer1 = new Customer();
    customer1.setId(1L);
    customer1.setFirstName("John");
    customer1.setLastName("Doe");
    customer1.setTelephone("+1234567890");

    Customer customer2 = new Customer();
    customer2.setId(1L);
    customer2.setFirstName("John");
    customer2.setLastName("Doe");
    customer2.setTelephone("+1234567890");

    assertThat(customer1).isEqualTo(customer2);
  }

  @Test
  void testNotEquals() {
    Customer customer1 = new Customer();
    customer1.setId(1L);
    customer1.setFirstName("JohnNy");
    customer1.setLastName("Doe");
    customer1.setTelephone("+1234567890");

    Customer customer2 = new Customer();
    customer2.setId(1L);
    customer2.setFirstName("John");
    customer2.setLastName("Doe");
    customer2.setTelephone("+1234567890");

    assertThat(customer1).isNotEqualTo(customer2);
  }

  @Test
  void testHashCode() {
    Customer customer1 = new Customer();
    customer1.setId(1L);
    customer1.setFirstName("John");
    customer1.setLastName("Doe");
    customer1.setTelephone("+1234567890");

    Customer customer2 = new Customer();
    customer2.setId(1L);
    customer2.setFirstName("John");
    customer2.setLastName("Doe");
    customer2.setTelephone("+1234567890");

    assertThat(customer1.hashCode()).isEqualTo(customer2.hashCode());
  }

  @Test
  void testToString() {
    Customer customer = new Customer();
    customer.setId(1L);
    customer.setFirstName("John");
    customer.setLastName("Doe");
    customer.setTelephone("+1234567890");

    String expected = "Customer(id=1, firstName=John, lastName=Doe, telephone=+1234567890)";
    assertThat(customer.toString()).isEqualTo(expected);
  }
}
