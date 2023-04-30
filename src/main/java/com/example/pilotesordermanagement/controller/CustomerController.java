package com.example.pilotesordermanagement.controller;

import com.example.pilotesordermanagement.dto.CustomerDto;
import com.example.pilotesordermanagement.service.CustomerService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  private final CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping
  public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
    CustomerDto createdCustomer = customerService.createCustomer(customerDto);
    return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
    CustomerDto customerDto = customerService.getCustomerById(id);
    return new ResponseEntity<>(customerDto, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<CustomerDto>> getAllCustomers() {
    List<CustomerDto> customers = customerService.getAllCustomers();
    return new ResponseEntity<>(customers, HttpStatus.OK);
  }
}
