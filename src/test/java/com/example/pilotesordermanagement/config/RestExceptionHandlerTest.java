package com.example.pilotesordermanagement.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.pilotesordermanagement.dto.ErrorResponseDto;
import com.example.pilotesordermanagement.exception.CustomerNotFoundException;
import com.example.pilotesordermanagement.exception.CustomerValidationException;
import com.example.pilotesordermanagement.exception.OrderNotFoundException;
import com.example.pilotesordermanagement.exception.OrderValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class RestExceptionHandlerTest {

  private RestExceptionHandler restExceptionHandler;

  @BeforeEach
  public void setUp() {
    restExceptionHandler = new RestExceptionHandler();
  }

  @Test
  public void testHandleOrderNotFoundException() {
    OrderNotFoundException exception = new OrderNotFoundException("1L");
    ResponseEntity<ErrorResponseDto> response = restExceptionHandler.handleConflict(exception);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(exception.getMessage(), response.getBody().getMessage());
  }

  @Test
  public void testHandleCustomerNotFoundException() {
    CustomerNotFoundException exception = new CustomerNotFoundException(1L);
    ResponseEntity<ErrorResponseDto> response = restExceptionHandler.handleConflict(exception);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(exception.getMessage(), response.getBody().getMessage());
  }

  @Test
  public void testHandleOrderValidationException() {
    OrderValidationException exception = new OrderValidationException("Invalid order");
    ResponseEntity<ErrorResponseDto> response = restExceptionHandler.handleConflict(exception);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(exception.getMessage(), response.getBody().getMessage());
  }

  @Test
  public void testHandleCustomerValidationException() {
    CustomerValidationException exception = new CustomerValidationException("Invalid customer");
    ResponseEntity<ErrorResponseDto> response = restExceptionHandler.handleConflict(exception);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(exception.getMessage(), response.getBody().getMessage());
  }

  @Test
  public void testHandleMethodArgumentNotValid() {
    BindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "objectName");
    FieldError fieldError = new FieldError("objectName", "field", "Rejected value");
    bindingResult.addError(fieldError);
    MethodArgumentNotValidException exception =
        new MethodArgumentNotValidException((MethodParameter) null, bindingResult);
    ResponseEntity<Object> response =
        restExceptionHandler.handleMethodArgumentNotValid(
            exception, null, HttpStatus.BAD_REQUEST, null);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    ErrorResponseDto errorResponseDto = (ErrorResponseDto) response.getBody();
    assertEquals("field: Rejected value", errorResponseDto.getMessage());
  }
}
