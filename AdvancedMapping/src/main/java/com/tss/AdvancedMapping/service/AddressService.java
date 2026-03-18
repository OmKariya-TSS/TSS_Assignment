package com.tss.AdvancedMapping.service;

import com.tss.AdvancedMapping.dto.AddressRequestDTO;
import com.tss.AdvancedMapping.dto.AddressResponseDTO;
import com.tss.AdvancedMapping.entity.Address;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressService {
    AddressResponseDTO saveAddress(AddressRequestDTO addressRequestDTO);
    AddressResponseDTO updateAddress(long studentId,AddressRequestDTO addressRequestDTO);
    Page<AddressResponseDTO> findAllAddress(Pageable pageable);
    AddressResponseDTO findByStudentId(long id);
    AddressResponseDTO findById(long id);
    Page<AddressResponseDTO> findByCity(String city, Pageable pageable);
   // AddressResponseDTO updateByRollNumber(Integer rollNumber, AddressRequestDTO addressRequestDTO);
}
