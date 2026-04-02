package com.tss.AdvancedMapping.controller;


import com.tss.AdvancedMapping.dto.request.InstructorRequestDTO;
import com.tss.AdvancedMapping.dto.response.InstructorResponseDTO;
import com.tss.AdvancedMapping.service.interfaces.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/findByChar")
    public ResponseEntity<Page<InstructorResponseDTO>> findByCharacter(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                                       @RequestParam(defaultValue = "10") Integer pageSize,@RequestParam Character character){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.status(200).body(instructorService.findByStartCharacter(character,pageable));
    }
}
