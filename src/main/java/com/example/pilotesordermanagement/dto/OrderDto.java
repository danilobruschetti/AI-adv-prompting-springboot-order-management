package com.example.pilotesordermanagement.dto;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
