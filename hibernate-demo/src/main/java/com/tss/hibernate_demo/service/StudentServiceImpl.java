package com.tss.hibernate_demo.service;

import com.tss.hibernate_demo.DAO.StudentRepository;
import com.tss.hibernate_demo.dto.page.StudentResponsePageDTO;
import com.tss.hibernate_demo.dto.request.StudentRequestDTO;
import com.tss.hibernate_demo.dto.response.StudentResponseDTO;
import com.tss.hibernate_demo.entity.Student;
import com.tss.hibernate_demo.exception.ResourceNotFoundException;
import com.tss.hibernate_demo.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

//    private ObjectMapper objectMapper;
//    Student student = objectMapper.convertValue(new StudentResponseDTO(),new Student.class);
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    public StudentServiceImpl(StudentRepository studentRepository,StudentMapper studentMapper) {
//        this.studentRepository = studentRepository;
//        this.studentMapper = studentMapper;
//    }

    @Override
    public StudentResponseDTO saveStudent(StudentRequestDTO studentDto) {
        Student student = studentMapper.toDto(studentDto);
        Student savedStudent = studentRepository.save(student);
        logger.info("Saved student {}", savedStudent);
        return studentMapper.toResponseDTO(savedStudent);
    }

    @Override
    public Page<StudentResponseDTO> getAllStudents(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Student> studentPage =studentRepository.findAll(pageable);
        logger.info("Found {} students", studentPage.getTotalElements());
        return studentPage.map(studentMapper::toResponseDTO);
    }

    @Override
    public Optional<StudentResponseDTO> getStudentById(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("STUDENT NOT FOUND",id));
        logger.info("Found student {}", student);
        return Optional.of(studentMapper.toResponseDTO(student));
    }
    @Override
    public StudentResponseDTO updateStudent(Integer id, Student student) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " , id));
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        Student updatedStudent = studentRepository.save(existingStudent);
        logger.info("Updated student {}", updatedStudent);
        return studentMapper.toResponseDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Integer id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " , id);
        }
        logger.info("Deleted student {}", studentRepository.findById(id).get());
        studentRepository.deleteById(id);
    }
    @Override
    public Page<StudentResponseDTO> getStudentsByName(String name, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<StudentResponseDTO> studentPage = studentRepository.findByName(name, pageable);
        logger.info("Found {} students", studentPage.getTotalElements());
        return studentPage;
    }

    @Override
    public Page<StudentResponseDTO> findByAge(Integer age, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<StudentResponseDTO> studentPage = studentRepository.findByAge(age, pageable);
        logger.info("Found {} students", studentPage.getTotalElements());
        return studentPage;
    }
    @Override
    public StudentResponsePageDTO getAllStudentsPage(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        StudentResponsePageDTO studentResponsePageDTOS = studentMapper.toResponsePageDTO(studentPage);
        logger.info("Found {} students", studentResponsePageDTOS.getTotalElements());
//        return StudentResponsePageDTO.builder()
//                .content(studentPage.stream()
//                        .map(studentMapper::toResponseDTO)
//                        .toList())
//                .numberOfElements(studentPage.getNumberOfElements())
//                .totalElements(studentPage.getTotalElements())
//                .totalPages(studentPage.getTotalPages())
//                .first(studentPage.isFirst())
//                .last(studentPage.isLast())
//                .pageNumber(studentPage.getNumber())
//                .pageSize(studentPage.getSize())
//                .build();
        return studentResponsePageDTOS;
    }
}