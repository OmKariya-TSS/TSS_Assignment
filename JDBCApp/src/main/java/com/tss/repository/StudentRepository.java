package com.tss.repository;


import com.tss.entity.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> readStudents();
    void addNewStudent(Student student);
}
