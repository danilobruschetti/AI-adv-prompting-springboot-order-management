package com.example.pilotesordermanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderDto {
  private Long id;

  @NotNull(message = "Customer ID must not be null")
  private Long customerId;

  @NotBlank(message = "Delivery address must not be blank")
  private String deliveryAddress;

  @NotNull(message = "Number of pilotes must not be null")
  @Min(value = 5, message = "Number of pilotes must be 5, 10, or 15")
  private Integer numberOfPilotes;

  private BigDecimal orderTotal;
}
