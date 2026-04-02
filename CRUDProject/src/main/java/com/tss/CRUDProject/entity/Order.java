package com.tss.CRUDProject.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;
    @ManyToMany
    @JoinTable(name = "order_product",
    joinColumns = @JoinColumn(name="orderId"),
    inverseJoinColumns = @JoinColumn(name = "productId"))
    private List<Product> products;

}
