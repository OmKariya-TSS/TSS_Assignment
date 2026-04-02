package com.tss.CRUDProject.service;

import com.tss.CRUDProject.dto.request.CustomerRequestDTO;
import com.tss.CRUDProject.dto.response.CustomerResponseDTO;
import com.tss.CRUDProject.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO getCustomerById(Long id);
    CustomerResponseDTO updateCustomer(Long  id, CustomerRequestDTO customerRequestDTO);
    void deleteCustomer(Long id);
    Page<CustomerResponseDTO> getAllCustomers(Pageable pageable);
}
