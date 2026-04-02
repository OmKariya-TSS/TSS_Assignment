package com.tss.CRUDProject.service;

import com.tss.CRUDProject.dto.request.ProductRequestDTO;
import com.tss.CRUDProject.dto.response.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
    ProductResponseDTO getProductById(Long id);
    ProductResponseDTO updateProduct(Long productId,ProductRequestDTO productRequestDTO);
    void deleteProduct(Long id);
    Page<ProductResponseDTO> getAllProducts(Pageable pageable);
}
