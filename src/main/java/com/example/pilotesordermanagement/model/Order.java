package com.example.pilotesordermanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Customer must not be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotBlank(message = "Delivery address must not be blank")
    @Column(nullable = false)
    private String deliveryAddress;

    @NotNull(message = "Number of pilotes must not be null")
    @Column(nullable = false)
    private Integer numberOfPilotes;

    @NotNull(message = "Order total must not be null")
    @Column(nullable = false)
    private BigDecimal orderTotal;

    @NotNull(message = "Creation time must not be null")
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
