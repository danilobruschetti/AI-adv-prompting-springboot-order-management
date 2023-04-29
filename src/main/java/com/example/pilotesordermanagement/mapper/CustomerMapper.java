package com.example.pilotesordermanagement.mapper;

import com.example.pilotesordermanagement.dto.CustomerDto;
import com.example.pilotesordermanagement.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "id", ignore = true)
    Customer toCustomer(CustomerDto customerDto);

    CustomerDto toCustomerDto(Customer customer);
}
