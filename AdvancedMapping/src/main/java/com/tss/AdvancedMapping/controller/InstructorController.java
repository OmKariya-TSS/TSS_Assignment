package com.tss.AdvancedMapping.controller;


import com.tss.AdvancedMapping.dto.CourseRequestDTO;
import com.tss.AdvancedMapping.dto.CourseResponseDTO;
import com.tss.AdvancedMapping.dto.InstructorRequestDTO;
import com.tss.AdvancedMapping.dto.InstructorResponseDTO;
import com.tss.AdvancedMapping.entity.Instructor;
import com.tss.AdvancedMapping.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;
    @PostMapping("/add")
    public ResponseEntity<InstructorResponseDTO> createInstructor(@RequestBody InstructorRequestDTO instructorRequestDTO) {
        return ResponseEntity.status(201).body(instructorService.createInstructor(instructorRequestDTO));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<InstructorResponseDTO> findById(@PathVariable long id) {
        return ResponseEntity.status(200).body(instructorService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<InstructorResponseDTO>> findAll(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                               @RequestParam(defaultValue = "10") Integer pageSize ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.status(200).body(instructorService.findAll(pageable));
    }
}
