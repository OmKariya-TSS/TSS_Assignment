package com.tss.service;


import com.tss.entity.Student;
import com.tss.repository.StudentRepository;
import com.tss.repository.StudentRepositoryImpl;

import java.util.List;

public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepo = new StudentRepositoryImpl();
    @Override
    public List<Student> getAllStudents() {
        return studentRepo.readStudents();
    }

    @Override
    public void addNewStudent(Student student) {
        studentRepo.addNewStudent(student);
    }
}
