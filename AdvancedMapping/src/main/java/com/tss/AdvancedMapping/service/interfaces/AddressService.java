package com.tss.AdvancedMapping.service.interfaces;

import com.tss.AdvancedMapping.dto.request.AddressRequestDTO;
import com.tss.AdvancedMapping.dto.response.AddressResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {
    AddressResponseDTO saveAddress(AddressRequestDTO addressRequestDTO);
    AddressResponseDTO updateAddress(long studentId,AddressRequestDTO addressRequestDTO);
    Page<AddressResponseDTO> findAllAddress(Pageable pageable);
    AddressResponseDTO findByStudentId(long id);
    AddressResponseDTO findById(long id);
    Page<AddressResponseDTO> findByCity(String city, Pageable pageable);
    AddressResponseDTO updateByRollNumber(Integer rollNumber, AddressRequestDTO addressRequestDTO);
}
