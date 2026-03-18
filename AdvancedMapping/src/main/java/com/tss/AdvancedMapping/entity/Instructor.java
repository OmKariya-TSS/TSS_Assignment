package com.tss.AdvancedMapping.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name="instructors")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Instructor {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructorId;
    @Column
    private String name;
    @Column
    private String qualification;
    @Column
    @OneToMany(mappedBy = "instructor",fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    private List<Course> courses;
}
