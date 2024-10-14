package com.manikanta.microservices.project.OrderService.Mapper;

import com.manikanta.microservices.project.OrderService.DTO.OrderDTO;
import com.manikanta.microservices.project.OrderService.Entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoOrderMapper {

    AutoOrderMapper MAPPER = Mappers.getMapper(AutoOrderMapper.class);

    OrderDTO mapToDTO(Order order);
}
