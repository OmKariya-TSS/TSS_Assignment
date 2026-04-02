package com.tss.AdvancedMapping.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="addresses")
public class Address {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    private Integer pinCode;
    @OneToOne(mappedBy = "address")
    private Student student;
}
