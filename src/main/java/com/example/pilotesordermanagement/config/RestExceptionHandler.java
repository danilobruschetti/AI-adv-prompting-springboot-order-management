package com.example.pilotesordermanagement.config;

import static java.util.stream.Collectors.joining;

import com.example.pilotesordermanagement.dto.ErrorResponseDto;
import com.example.pilotesordermanagement.exception.CustomerNotFoundException;
import com.example.pilotesordermanagement.exception.CustomerValidationException;
import com.example.pilotesordermanagement.exception.OrderNotFoundException;
import com.example.pilotesordermanagement.exception.OrderValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {OrderNotFoundException.class})
  protected ResponseEntity<ErrorResponseDto> handleConflict(OrderNotFoundException ex) {
    return new ResponseEntity<>(new ErrorResponseDto(ex.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {CustomerNotFoundException.class})
  protected ResponseEntity<ErrorResponseDto> handleConflict(CustomerNotFoundException ex) {
    return new ResponseEntity<>(new ErrorResponseDto(ex.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {OrderValidationException.class})
  protected ResponseEntity<ErrorResponseDto> handleConflict(OrderValidationException ex) {
    return new ResponseEntity<>(new ErrorResponseDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {CustomerValidationException.class})
  protected ResponseEntity<ErrorResponseDto> handleConflict(CustomerValidationException ex) {
    return new ResponseEntity<>(new ErrorResponseDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {

    final String errors =
        ex.getBindingResult().getAllErrors().stream()
            .map(
                (error) ->
                    String.format(
                        "%s: %s", ((FieldError) error).getField(), error.getDefaultMessage()))
            .collect(joining(","));

    return new ResponseEntity<>(new ErrorResponseDto(errors), HttpStatus.BAD_REQUEST);
  }
}
