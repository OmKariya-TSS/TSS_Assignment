package com.tss.hibernate_demo.controller;

import com.tss.hibernate_demo.dto.page.StudentResponsePageDTO;
import com.tss.hibernate_demo.dto.request.StudentRequestDTO;
import com.tss.hibernate_demo.dto.response.StudentResponseDTO;
import com.tss.hibernate_demo.entity.Student;
import com.tss.hibernate_demo.exception.ResourceNotFoundException;
import com.tss.hibernate_demo.service.IStudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final IStudentService studentService;
    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @PostMapping("/add")
    public ResponseEntity<StudentResponseDTO> addStudent(@Valid @RequestBody StudentRequestDTO student) {
        StudentResponseDTO savedStudent = studentService.saveStudent(student);
        logger.info(savedStudent.toString());
        return ResponseEntity.status(201).body(savedStudent);
    }


    @GetMapping("/all")
    public ResponseEntity<Page<StudentResponseDTO>> getAllStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        if (name != null) {
            return ResponseEntity.ok(studentService.getStudentsByName(name, pageNumber, pageSize));
        }

        if (age != null) {
            return ResponseEntity.ok(studentService.findByAge(age, pageNumber, pageSize));
        }
        logger.info("got all students");
        return ResponseEntity.ok(studentService.getAllStudents(pageNumber, pageSize));
    }

    @GetMapping
    public ResponseEntity<StudentResponseDTO> getStudentById(@RequestParam Integer id) {
        StudentResponseDTO student = studentService.getStudentById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " , id));
        logger.info(student.toString());
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        StudentResponseDTO updatedStudent = studentService.updateStudent(id, student);
        logger.info(updatedStudent.toString());
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        studentService.getStudentById(id).orElseThrow(()->new ResourceNotFoundException("Student Not Found",id));
        studentService.deleteStudent(id);
        logger.info("deleted student with id: " + id);
        return ResponseEntity.ok("Student deleted successfully");
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Page<StudentResponseDTO>> getStudentsByName(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {

        Page<StudentResponseDTO> students = studentService.getStudentsByName(name, pageNumber, pageSize);
        logger.info(students.toString());
        return ResponseEntity.ok(students);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<Page<StudentResponseDTO>> getStudentsByAge(
            @PathVariable Integer age,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {

        Page<StudentResponseDTO> students = studentService.findByAge(age, pageNumber, pageSize);
        logger.info(students.toString());
        return ResponseEntity.ok(students);
    }
    @GetMapping("/page/all")
    public ResponseEntity<StudentResponsePageDTO> getAllStudentsPage(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        StudentResponsePageDTO studentsPage = studentService.getAllStudentsPage(pageNumber, pageSize);
        logger.info(studentsPage.toString());
        return ResponseEntity.ok(studentsPage);
    }
}