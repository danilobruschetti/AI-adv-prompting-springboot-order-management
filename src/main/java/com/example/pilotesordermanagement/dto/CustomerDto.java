package com.example.pilotesordermanagement.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerDto {
  private Long id;

  @NotBlank(message = "First name must not be blank")
  private String firstName;

  @NotBlank(message = "Last name must not be blank")
  private String lastName;

  @NotBlank(message = "Telephone number must not be blank")
  @Pattern(
      regexp = "^\\+?\\d{10,15}$",
      message = "Telephone number must be between 10 and 15 digits")
  private String telephone;
}
