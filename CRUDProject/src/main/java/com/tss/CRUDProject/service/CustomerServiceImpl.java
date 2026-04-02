package com.tss.CRUDProject.service;

import com.tss.CRUDProject.dao.CustomerRepository;
import com.tss.CRUDProject.dto.request.CustomerRequestDTO;
import com.tss.CRUDProject.dto.response.CustomerResponseDTO;
import com.tss.CRUDProject.entity.Customer;
import com.tss.CRUDProject.exception.ResourceNotFoundException;
import com.tss.CRUDProject.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.toCustomer(customerRequestDTO);
        return customerMapper.toCustomerResponseDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        return customerMapper.toCustomerResponseDTO(customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer not found",id)));
    }

    @Override
    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer not found",id));
        customer.setName(customerRequestDTO.getName());
        customer.setEmail(customerRequestDTO.getEmail());
        return customerMapper.toCustomerResponseDTO(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(Long id) {
       boolean exists=  customerRepository.existsById(id);
       if(!exists){
           throw new ResourceNotFoundException("Customer not found",id);
       }
       else{
           customerRepository.deleteById(id);
       }
    }

    @Override
    public Page<CustomerResponseDTO> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable).map(customerMapper::toCustomerResponseDTO);
    }
}
