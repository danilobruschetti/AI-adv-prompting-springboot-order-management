package com.example.pilotesordermanagement.controller;

import com.example.pilotesordermanagement.dto.CustomerDto;
import com.example.pilotesordermanagement.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/V1/customers")
@RequiredArgsConstructor
@Tag(name = "Customers", description = "Customer management APIs")
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping
  @Operation(
      summary = "Create a customer",
      description = "Create a customer of Miquel Montoro's Resturant")
  public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
    CustomerDto createdCustomer = customerService.createCustomer(customerDto);
    return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  @Operation(
      summary = "Find Customer By Id",
      description = "Find a customer of Miquel Montoro's Resturant")
  public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
    CustomerDto customerDto = customerService.getCustomerById(id);
    return new ResponseEntity<>(customerDto, HttpStatus.OK);
  }

  @GetMapping
  @Operation(
      summary = "Find All Customers",
      description = "Find all customer of Miquel Montoro's Resturant")
  public ResponseEntity<List<CustomerDto>> getAllCustomers() {
    List<CustomerDto> customers = customerService.getAllCustomers();
    return new ResponseEntity<>(customers, HttpStatus.OK);
  }
}
