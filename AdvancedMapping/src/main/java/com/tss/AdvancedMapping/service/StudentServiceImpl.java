package com.tss.AdvancedMapping.service;

import com.tss.AdvancedMapping.dto.StudentRequestDTO;
import com.tss.AdvancedMapping.dto.StudentResponseDTO;
import com.tss.AdvancedMapping.entity.Student;
import com.tss.AdvancedMapping.exception.ResourceNotFoundException;
import com.tss.AdvancedMapping.mapper.StudentMapper;
import com.tss.AdvancedMapping.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public StudentResponseDTO saveStudent(StudentRequestDTO student) {
        Student result = studentMapper.toDto(student);
        Student savedStudent = studentRepository.save(result);
        logger.info("Saving the student");
        return studentMapper.toResponseDTO(savedStudent);
    }

    @Override
    public Page<StudentResponseDTO> findAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable).map(studentMapper::toResponseDTO);
    }

    @Override
    public StudentResponseDTO findStudentById(long id) {
        if(!studentRepository.existsById(id)){
            throw new ResourceNotFoundException("student not found",id);
        }
        logger.info("finding students");
        return  studentMapper.toResponseDTO(studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("student not found",id)));
    }

    @Override
    public void deleteStudent(long id) {
        logger.info("Student deleting");
        studentRepository.deleteById(id);
    }

    @Override
    public Page<StudentResponseDTO> findByCity(String city,Pageable pageable) {
        if(!studentRepository.existsByAddressCity(city)){
            throw new ResourceNotFoundException("student not found",city);
        }
        logger.info("finding all students");
        return studentRepository.findByAddressCity(city,pageable).map(studentMapper::toResponseDTO);
    }

    @Override
    public StudentResponseDTO findByRollNumber(Integer rollNumber) {
        logger.info("finding student by roll number");
        return studentMapper.toResponseDTO(studentRepository.findByRollNumber(rollNumber).orElseThrow(()->new ResourceNotFoundException("student not found",rollNumber)));
    }


}
