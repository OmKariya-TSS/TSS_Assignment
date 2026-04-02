package com.tss.CRUDProject.service;

import com.tss.CRUDProject.dao.OrderRepository;
import com.tss.CRUDProject.dao.ProductRepository;
import com.tss.CRUDProject.dto.request.ProductRequestDTO;
import com.tss.CRUDProject.dto.response.ProductResponseDTO;
import com.tss.CRUDProject.entity.Product;
import com.tss.CRUDProject.exception.ResourceNotFoundException;
import com.tss.CRUDProject.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        return productMapper.toProductResponseDTO(productRepository.save(productMapper.toProduct(productRequestDTO)));
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("product",id));
        return productMapper.toProductResponseDTO(product);
    }

    @Override
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("product",productId));
        product.setProductName(productRequestDTO.getProductName());
        product.setProductDescription(productRequestDTO.getProductDescription());
        product.setProductPrice(productRequestDTO.getProductPrice());
        return  productMapper.toProductResponseDTO(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("product",id);
        }
        Product product  = productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("product",id));
        if (!product.getOrders().isEmpty()) {
            throw new IllegalStateException("Cannot delete product: It is associated with existing orders.");
        }
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(productMapper::toProductResponseDTO);
    }
}
