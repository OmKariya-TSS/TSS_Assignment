package com.tss.AdvancedMapping.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="students")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(unique = true)
    private Integer rollNumber;
    @Column
    private String name;
    @OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="addressId")
    private Address address;
}
