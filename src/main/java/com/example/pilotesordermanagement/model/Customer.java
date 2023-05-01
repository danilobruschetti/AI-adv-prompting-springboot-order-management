package com.example.pilotesordermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
  @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
  private Long id;

  @NotBlank(message = "First name must not be blank")
  @Column(nullable = false)
  private String firstName;

  @NotBlank(message = "Last name must not be blank")
  @Column(nullable = false)
  private String lastName;

  @NotBlank(message = "Telephone number must not be blank")
  @Pattern(
      regexp = "^\\+?\\d{10,15}$",
      message = "Telephone number must be between 10 and 15 digits")
  @Column(nullable = false, unique = true)
  private String telephone;
}
