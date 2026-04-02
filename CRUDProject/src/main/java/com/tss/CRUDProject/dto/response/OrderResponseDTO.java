package com.tss.CRUDProject.dto.response;

import com.tss.CRUDProject.entity.Customer;
import com.tss.CRUDProject.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private Long orderId;
    private String status;
    private Long customerId;
    private List<ProductResponseDTO> products;
    private Double totalAmount;
}
