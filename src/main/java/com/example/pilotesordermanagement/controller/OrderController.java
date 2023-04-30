package com.example.pilotesordermanagement.controller;

import com.example.pilotesordermanagement.dto.OrderDto;
import com.example.pilotesordermanagement.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/V1/orders")
@RequiredArgsConstructor
@Tag(name = "Pilotes Order", description = "Order management APIs")
public class OrderController {
  private final OrderService orderService;

  @PostMapping
  @Operation(
      summary = "Create an Order for a Customer",
      description = "Create an Order for a Customer of Miquel Montoro's Resturant")
  public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto) {
    OrderDto createdOrder = orderService.createOrder(orderDto);
    return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @Operation(
      summary = "Modify an Order for a Customer",
      description = "Order cannot be modified after 5 minutes from creation")
  public ResponseEntity<OrderDto> updateOrder(
      @PathVariable Long id, @Valid @RequestBody OrderDto orderDto) {
    OrderDto updatedOrder = orderService.updateOrder(id, orderDto);
    return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
  }

  @GetMapping("/search")
  @Operation(
      summary = "Search All Orders",
      description = "You can search all Orders only if you are ADMIN")
  public ResponseEntity<List<OrderDto>> searchOrdersByCustomerName(@RequestParam String query) {
    List<OrderDto> orders = orderService.searchOrdersByCustomerName(query);
    return new ResponseEntity<>(orders, HttpStatus.OK);
  }
}
