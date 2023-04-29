package com.example.pilotesordermanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name must not be blank")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = "Telephone number must not be blank")
    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "Telephone number must be between 10 and 15 digits")
    @Column(nullable = false, unique = true)
    private String telephone;
}
