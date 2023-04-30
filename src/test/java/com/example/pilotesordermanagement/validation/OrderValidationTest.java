package com.example.pilotesordermanagement.validation;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.pilotesordermanagement.dto.OrderDto;
import com.example.pilotesordermanagement.exception.CustomerValidationException;
import com.example.pilotesordermanagement.exception.OrderValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderValidationTest {

  @Test
  public void testValidate_throwsCustomerValidationException_whenCustomerIdIsNull() {
    OrderDto orderDto = new OrderDto();
    orderDto.setCustomerId(null);

    assertThrows(CustomerValidationException.class, () -> OrderValidation.validate(orderDto));
  }

  @Test
  public void testValidate_throwsOrderValidationException_whenNumberOfPilotesIsInvalid() {
    OrderDto orderDto = new OrderDto();
    orderDto.setCustomerId(1L);
    orderDto.setNumberOfPilotes(3);

    assertThrows(OrderValidationException.class, () -> OrderValidation.validate(orderDto));
  }

  @Test
  public void testValidate_throwsOrderValidationException_whenDeliveryAddressIsNull() {
    OrderDto orderDto = new OrderDto();
    orderDto.setCustomerId(1L);
    orderDto.setNumberOfPilotes(5);
    orderDto.setDeliveryAddress(null);

    assertThrows(OrderValidationException.class, () -> OrderValidation.validate(orderDto));
  }

  @Test
  public void testValidate_throwsOrderValidationException_whenDeliveryAddressIsEmpty() {
    OrderDto orderDto = new OrderDto();
    orderDto.setCustomerId(1L);
    orderDto.setNumberOfPilotes(5);
    orderDto.setDeliveryAddress("");

    assertThrows(OrderValidationException.class, () -> OrderValidation.validate(orderDto));
  }

  @Test
  public void testValidate_success() {
    OrderDto orderDto = new OrderDto();
    orderDto.setCustomerId(1L);
    orderDto.setNumberOfPilotes(5);
    orderDto.setDeliveryAddress("123 Main St");

    OrderValidation.validate(orderDto); // No exception is thrown
  }
}
