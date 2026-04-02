package com.tss.CRUDProject.dto.response;


import com.tss.CRUDProject.entity.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {
    private Long customerId;
    private String name;
    private String email;
    private List<OrderResponseDTO> orders;
}
