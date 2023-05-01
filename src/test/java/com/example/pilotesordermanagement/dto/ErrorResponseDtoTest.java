package com.example.pilotesordermanagement.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ErrorResponseDtoTest {

  private ErrorResponseDto errorResponseDto;

  @BeforeEach
  public void setUp() {
    errorResponseDto = new ErrorResponseDto("Error message");
  }

  @Test
  public void testToString() {
    String expectedString = "ErrorResponseDto(message=Error message)";
    String actualString = errorResponseDto.toString();
    Assertions.assertEquals(expectedString, actualString);
  }

  @Test
  public void testEqualsAndHashCode() {
    ErrorResponseDto sameErrorResponseDto = new ErrorResponseDto("Error message");
    ErrorResponseDto differentErrorResponseDto = new ErrorResponseDto("Different error message");

    // Test equals method
    Assertions.assertEquals(errorResponseDto, sameErrorResponseDto);
    Assertions.assertNotEquals(errorResponseDto, differentErrorResponseDto);

    // Test hashCode method
    Assertions.assertEquals(errorResponseDto.hashCode(), sameErrorResponseDto.hashCode());
    Assertions.assertNotEquals(errorResponseDto.hashCode(), differentErrorResponseDto.hashCode());
  }
}
