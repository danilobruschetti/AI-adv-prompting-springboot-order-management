package com.example.pilotesordermanagement.controller;

import com.example.pilotesordermanagement.dto.OrderDto;
import com.example.pilotesordermanagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderService.createOrder(orderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto) {
        OrderDto updatedOrder = orderService.updateOrder(id, orderDto);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('search:orders')")
    public ResponseEntity<List<OrderDto>> searchOrdersByCustomerName(@RequestParam String query) {
        List<OrderDto> orders = orderService.searchOrdersByCustomerName(query);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
