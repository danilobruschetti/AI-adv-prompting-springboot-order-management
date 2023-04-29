package com.example.pilotesordermanagement.service;

import com.example.pilotesordermanagement.dto.CustomerDto;
import com.example.pilotesordermanagement.exception.CustomerNotFoundException;
import com.example.pilotesordermanagement.mapper.CustomerMapper;
import com.example.pilotesordermanagement.model.Customer;
import com.example.pilotesordermanagement.repository.CustomerRepository;
import com.example.pilotesordermanagement.validation.CustomerValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerDto getCustomerById(Long id) {
        return customerMapper.toCustomerDto(customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id)));
    }

    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        CustomerValidation.validate(customerDto);
        Customer customer = customerMapper.toCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toCustomerDto(savedCustomer);
    }

    @Transactional(readOnly = true)
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toCustomerDto)
                .collect(Collectors.toList());
    }
}
