package com.tss.CRUDProject.mapper;

import com.tss.CRUDProject.dto.request.ProductRequestDTO;
import com.tss.CRUDProject.dto.response.ProductResponseDTO;
import com.tss.CRUDProject.entity.Order;
import com.tss.CRUDProject.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductRequestDTO productRequestDTO);
    ProductResponseDTO toProductResponseDTO(Product product);
}
