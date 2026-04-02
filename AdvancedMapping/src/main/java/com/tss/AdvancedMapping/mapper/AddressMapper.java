package com.tss.AdvancedMapping.mapper;

import com.tss.AdvancedMapping.dto.request.AddressRequestDTO;
import com.tss.AdvancedMapping.dto.response.AddressResponseDTO;
import com.tss.AdvancedMapping.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toDTO(AddressRequestDTO addressRequestDTO);
    AddressResponseDTO toResponseDTO(Address address);
}
