package com.example.pilotesordermanagement.validation;

import com.example.pilotesordermanagement.dto.CustomerDto;
import com.example.pilotesordermanagement.exception.CustomerValidationException;

public class CustomerValidation {

  public static void validate(CustomerDto customerDto) {
    if (customerDto.getFirstName() == null || customerDto.getFirstName().trim().isEmpty()) {
      throw new CustomerValidationException("First name is required.");
    }

    if (customerDto.getLastName() == null || customerDto.getLastName().trim().isEmpty()) {
      throw new CustomerValidationException("Last name is required.");
    }

    if (customerDto.getTelephone() == null || customerDto.getTelephone().trim().isEmpty()) {
      throw new CustomerValidationException("Phone number is required.");
    }
  }
}
