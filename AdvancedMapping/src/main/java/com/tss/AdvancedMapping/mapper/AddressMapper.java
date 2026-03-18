package com.tss.AdvancedMapping.mapper;

import com.tss.AdvancedMapping.dto.AddressRequestDTO;
import com.tss.AdvancedMapping.dto.AddressResponseDTO;
import com.tss.AdvancedMapping.entity.Address;
import com.tss.AdvancedMapping.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toDTO(AddressRequestDTO addressRequestDTO);
    AddressResponseDTO toResponseDTO(Address address);
}
