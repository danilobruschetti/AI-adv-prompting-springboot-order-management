package com.example.pilotesordermanagement.config;

import com.example.pilotesordermanagement.dto.ErrorResponseDto;
import com.example.pilotesordermanagement.exception.OrderNotFoundException;
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

import static java.util.stream.Collectors.joining;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = {OrderNotFoundException.class})
    protected ResponseEntity<ErrorResponseDto> handleConflict(OrderNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getMessage()), HttpStatus.NOT_FOUND);
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

