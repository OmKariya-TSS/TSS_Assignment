package com.tss.hibernate_demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer student_id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}