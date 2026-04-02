package com.tss.AdvancedMapping.controller;

import com.tss.AdvancedMapping.dto.request.CourseRequestDTO;
import com.tss.AdvancedMapping.dto.request.StudentRequestDTO;
import com.tss.AdvancedMapping.dto.response.StudentResponseDTO;
import com.tss.AdvancedMapping.service.interfaces.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity<StudentResponseDTO> addStudent(@Valid @RequestBody StudentRequestDTO student) {
        StudentResponseDTO savedStudent = studentService.saveStudent(student);
        return ResponseEntity.status(201).body(savedStudent);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<StudentResponseDTO>> getAllStudents(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                                    @RequestParam(defaultValue = "3") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.status(200).body(studentService.findAllStudents(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable long id) {
        return ResponseEntity.status(200).body(studentService.findStudentById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(2000).body("Deleted student with id " + id);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<Page<StudentResponseDTO>> getStudentsByCity(@PathVariable String city,@RequestParam(defaultValue = "0") Integer pageNumber,
                                                                      @RequestParam(defaultValue = "3") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.status(200).body(studentService.findByCity(city,pageable));
    }

    @GetMapping("rollNumber/{rollNumber}")
    public ResponseEntity<StudentResponseDTO> findByRollNumber(@PathVariable Integer rollNumber) {
        return ResponseEntity.status(200).body(studentService.findByRollNumber(rollNumber));
    }

    @PutMapping("assignCourse")
    public ResponseEntity<StudentResponseDTO> assignCourse(@RequestParam Long courseId,@RequestParam Long studentId) {
        return ResponseEntity.status(200).body(studentService.assignCourseToStudent(studentId, courseId));
    }

    @PutMapping("updateCourse")
    public ResponseEntity<StudentResponseDTO> updateCourse(@RequestParam Long studentId,@RequestParam Long oldCourseId,@RequestParam Long newCourseId) {
        return ResponseEntity.status(200).body(studentService.updateCourse(oldCourseId,studentId,newCourseId));
    }
}
