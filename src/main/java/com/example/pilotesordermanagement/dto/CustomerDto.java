package com.example.pilotesordermanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
