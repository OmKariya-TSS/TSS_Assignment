package com.tss.CRUDProject.controller;


import com.tss.CRUDProject.dto.request.CustomerRequestDTO;
import com.tss.CRUDProject.dto.response.CustomerResponseDTO;
import com.tss.CRUDProject.entity.Customer;
import com.tss.CRUDProject.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping("/add")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO customer = customerService.createCustomer(customerRequestDTO);
        return ResponseEntity.ok().body(customer);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable Long id) {
        CustomerResponseDTO customer = customerService.getCustomerById(id);
        return ResponseEntity.ok().body(customer);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable Long id,@RequestBody CustomerRequestDTO customerRequestDTO) {
        return ResponseEntity.ok().body(customerService.updateCustomer(id, customerRequestDTO));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().body("Customer deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<Page<CustomerResponseDTO>> getAllCustomers(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                                     @RequestParam(defaultValue = "3") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.ok().body(customerService.getAllCustomers(pageable));
    }
}
