package com.tss.AdvancedMapping.controller;


import com.tss.AdvancedMapping.dto.AddressRequestDTO;
import com.tss.AdvancedMapping.dto.AddressResponseDTO;
import com.tss.AdvancedMapping.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    @PostMapping("/add")
    public ResponseEntity<AddressResponseDTO> addStudent(@Valid @RequestBody AddressRequestDTO address) {
        AddressResponseDTO savedAddress = addressService.saveAddress(address);
        return ResponseEntity.status(201).body(savedAddress);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@Valid @RequestBody AddressRequestDTO addressRequestDTO, @PathVariable long id) {
        return ResponseEntity.status(201).body(addressService.updateAddress(id, addressRequestDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<AddressResponseDTO>> findAllAddress(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                                   @RequestParam(defaultValue = "3") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.status(200).body(addressService.findAllAddress(pageable));
    }

    @GetMapping("find/student/{id}")
    public ResponseEntity<AddressResponseDTO> findByStudentId(@PathVariable long id) {
        return ResponseEntity.status(200).body(addressService.findByStudentId(id));
    }

    @GetMapping("id/{id}")
    public ResponseEntity<AddressResponseDTO> findById(@PathVariable long id) {
        return ResponseEntity.status(200).body(addressService.findById(id));
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<Page<AddressResponseDTO>> findByCity(@PathVariable String city,@RequestParam(defaultValue = "0") Integer pageNumber,
                                                               @RequestParam(defaultValue = "3") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return  ResponseEntity.status(200).body(addressService.findByCity(city,pageable));
    }

//    @PutMapping("rollNumber/{rollNumber}")
//    public ResponseEntity<AddressResponseDTO> updateByRollNumber(Integer rollNumber, @Valid @RequestBody AddressRequestDTO addressRequestDTO) {
//        return ResponseEntity.status(200).body(addressService.updateByRollNumber(rollNumber, addressRequestDTO));
//    }

}
