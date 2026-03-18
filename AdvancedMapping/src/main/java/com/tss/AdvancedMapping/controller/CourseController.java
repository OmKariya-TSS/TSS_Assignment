package com.tss.AdvancedMapping.controller;


import com.tss.AdvancedMapping.dto.CourseRequestDTO;
import com.tss.AdvancedMapping.dto.CourseResponseDTO;
import com.tss.AdvancedMapping.dto.InstructorResponseDTO;
import com.tss.AdvancedMapping.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseRequestDTO courseRequestDTO) {
        return ResponseEntity.status(201).body(courseService.createCourse(courseRequestDTO));
    }

    @GetMapping("id/{id}")
    public ResponseEntity<CourseResponseDTO> findById(@PathVariable long id) {
        return ResponseEntity.status(200).body(courseService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<CourseResponseDTO>> findAll(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                           @RequestParam(defaultValue = "10") Integer pageSize ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.status(200).body(courseService.findAll(pageable));
    }

    @PutMapping("/assign/instructor")
    public ResponseEntity<CourseResponseDTO> assignInstructorToCourse(@RequestParam Long intructorId,@RequestParam Long courseId) {
        return ResponseEntity.status(200).body(courseService.assignInstructorToCourse(intructorId,courseId));
    }

    @PutMapping("/assign/course")
    public ResponseEntity<InstructorResponseDTO> assignCourseToInstructor(@RequestParam Long instructorId,@RequestParam Long courseId) {
        return ResponseEntity.status(200).body(courseService.assignCourseToInstructor(instructorId,courseId));
    }

}
