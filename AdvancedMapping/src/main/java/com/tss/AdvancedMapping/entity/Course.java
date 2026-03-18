package com.tss.AdvancedMapping.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long courseId;
    @Column
    private String courseName;
    @Column
    private Integer duration;
    @Column
    private Double fees;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
}
