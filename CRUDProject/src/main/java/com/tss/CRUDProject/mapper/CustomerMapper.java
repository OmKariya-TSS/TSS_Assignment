package com.tss.CRUDProject.mapper;

import com.tss.CRUDProject.dto.request.CustomerRequestDTO;
import com.tss.CRUDProject.dto.response.CustomerResponseDTO;
import com.tss.CRUDProject.dto.response.OrderResponseDTO;
import com.tss.CRUDProject.entity.Customer;
import com.tss.CRUDProject.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {ProductMapper.class,OrderMapper.class})
public interface CustomerMapper {
    CustomerResponseDTO toCustomerResponseDTO(Customer customer);
    Customer toCustomer(CustomerRequestDTO customerRequestDTO);
    List<OrderResponseDTO> toOrderResponseDTO(List<Order> orders);
}
