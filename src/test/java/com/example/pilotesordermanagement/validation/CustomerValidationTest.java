package com.example.pilotesordermanagement.validation;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.pilotesordermanagement.dto.CustomerDto;
import com.example.pilotesordermanagement.exception.CustomerValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerValidationTest {

  @Test
  public void testValidate_throwsCustomerValidationException_whenFirstNameIsNull() {
    CustomerDto customerDto = new CustomerDto();
    customerDto.setFirstName(null);

    assertThrows(CustomerValidationException.class, () -> CustomerValidation.validate(customerDto));
  }

  @Test
  public void testValidate_throwsCustomerValidationException_whenFirstNameIsEmpty() {
    CustomerDto customerDto = new CustomerDto();
    customerDto.setFirstName("");

    assertThrows(CustomerValidationException.class, () -> CustomerValidation.validate(customerDto));
  }

  @Test
  public void testValidate_throwsCustomerValidationException_whenLastNameIsNull() {
    CustomerDto customerDto = new CustomerDto();
    customerDto.setFirstName("John");
    customerDto.setLastName(null);

    assertThrows(CustomerValidationException.class, () -> CustomerValidation.validate(customerDto));
  }

  @Test
  public void testValidate_throwsCustomerValidationException_whenLastNameIsEmpty() {
    CustomerDto customerDto = new CustomerDto();
    customerDto.setFirstName("John");
    customerDto.setLastName("");

    assertThrows(CustomerValidationException.class, () -> CustomerValidation.validate(customerDto));
  }

  @Test
  public void testValidate_throwsCustomerValidationException_whenTelephoneIsNull() {
    CustomerDto customerDto = new CustomerDto();
    customerDto.setFirstName("John");
    customerDto.setLastName("Doe");
    customerDto.setTelephone(null);

    assertThrows(CustomerValidationException.class, () -> CustomerValidation.validate(customerDto));
  }

  @Test
  public void testValidate_throwsCustomerValidationException_whenTelephoneIsEmpty() {
    CustomerDto customerDto = new CustomerDto();
    customerDto.setFirstName("John");
    customerDto.setLastName("Doe");
    customerDto.setTelephone("");

    assertThrows(CustomerValidationException.class, () -> CustomerValidation.validate(customerDto));
  }

  @Test
  public void testValidate_success() {
    CustomerDto customerDto = new CustomerDto();
    customerDto.setFirstName("John");
    customerDto.setLastName("Doe");
    customerDto.setTelephone("+1234567890");

    CustomerValidation.validate(customerDto); // No exception is thrown
  }
}
