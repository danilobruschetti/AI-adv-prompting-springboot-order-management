package com.example.pilotesordermanagement.mapper;

import com.example.pilotesordermanagement.dto.OrderDto;
import com.example.pilotesordermanagement.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CustomerMapper.class})
public interface OrderMapper {
  OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "orderTotal", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  Order toOrder(OrderDto orderDto);

  OrderDto toOrderDto(Order order);
}
