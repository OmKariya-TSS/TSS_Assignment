package com.tss.CRUDProject.mapper;

import com.tss.CRUDProject.dto.request.OrderRequestDTO;
import com.tss.CRUDProject.dto.response.OrderResponseDTO;
import com.tss.CRUDProject.dto.response.ProductResponseDTO;
import com.tss.CRUDProject.entity.Order;
import com.tss.CRUDProject.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = ProductMapper.class)
public interface OrderMapper {
    List<ProductResponseDTO> toProductResponseDTO(List<Product> products);
    @Mapping(source="customerId",target="customer.customerId")
    Order toOrder(OrderRequestDTO orderRequestDTO);
    @Mapping(source = "customer.customerId", target = "customerId")
    @Mapping(target = "totalAmount", expression = "java(calculateTotal(order))")
    OrderResponseDTO toOrderResponseDTO(Order order);

    default Double calculateTotal(Order order) {
        if (order.getProducts() == null) return 0.0;
        return order.getProducts().stream()
                .mapToDouble(Product::getProductPrice)
                .sum();
    }

}
