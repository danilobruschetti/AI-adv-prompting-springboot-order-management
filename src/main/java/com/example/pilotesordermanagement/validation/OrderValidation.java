package com.example.pilotesordermanagement.validation;

import com.example.pilotesordermanagement.dto.OrderDto;
import com.example.pilotesordermanagement.exception.CustomerValidationException;

public class OrderValidation {

  public static void validate(OrderDto orderDto) {
    if (orderDto.getCustomerId() == null) {
      throw new CustomerValidationException("Customer ID is required.");
    }

    if (orderDto.getNumberOfPilotes() == null
        || orderDto.getNumberOfPilotes() < 5
        || orderDto.getNumberOfPilotes() > 15
        || orderDto.getNumberOfPilotes() % 5 != 0) {
      throw new CustomerValidationException("Number of pilotes must be 5, 10 or 15.");
    }

    if (orderDto.getDeliveryAddress() == null || orderDto.getDeliveryAddress().trim().isEmpty()) {
      throw new CustomerValidationException("Delivery address is required.");
    }
  }
}
