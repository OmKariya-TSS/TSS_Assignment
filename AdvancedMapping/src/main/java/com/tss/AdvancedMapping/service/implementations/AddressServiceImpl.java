package com.tss.AdvancedMapping.service.implementations;

import com.tss.AdvancedMapping.dto.request.AddressRequestDTO;
import com.tss.AdvancedMapping.dto.response.AddressResponseDTO;
import com.tss.AdvancedMapping.entity.Address;
import com.tss.AdvancedMapping.entity.Student;
import com.tss.AdvancedMapping.exception.ResourceNotFoundException;
import com.tss.AdvancedMapping.mapper.AddressMapper;
import com.tss.AdvancedMapping.repository.AddressRepository;
import com.tss.AdvancedMapping.repository.StudentRepository;
import com.tss.AdvancedMapping.service.interfaces.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final StudentRepository studentRepository;
    private final AddressMapper addressMapper;

    @Override
    public AddressResponseDTO saveAddress(AddressRequestDTO addressRequestDTO) {
        Address result = addressMapper.toDTO(addressRequestDTO);
        Address savedAddress = addressRepository.save(result);
        return addressMapper.toResponseDTO(savedAddress);
    }

    @Override
    public AddressResponseDTO updateAddress(long studentId, AddressRequestDTO addressRequestDTO) {
        Address got = addressRepository.findByStudentId(studentId).orElseThrow(()->new ResourceNotFoundException("adddress no found",studentId));
        got.setCity(addressRequestDTO.getCity());
        got.setState(addressRequestDTO.getState());
        got.setPinCode(addressRequestDTO.getPinCode());
        Address updatedAddress = addressRepository.save(got);
        return addressMapper.toResponseDTO(updatedAddress);
    }

    @Override
    public Page<AddressResponseDTO> findAllAddress(Pageable pageable) {
        Page<Address> addresses =addressRepository.findAll(pageable);
        return addresses.map(addressMapper::toResponseDTO);
    }

    @Override
    public AddressResponseDTO findByStudentId(long id) {
        return addressMapper.toResponseDTO(addressRepository.findByStudentId(id).orElseThrow(()->new ResourceNotFoundException("address no found",id)));
    }

    @Override
    public AddressResponseDTO findById(long id) {
        return addressMapper.toResponseDTO(addressRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("address no found",id)));
    }

    @Override
    public Page<AddressResponseDTO> findByCity(String city,Pageable pageable) {
        if(!addressRepository.existsByCity(city)){
            throw new ResourceNotFoundException("address city no found",city);
        }
        Page<Address> addresses =addressRepository.findByCity(city,pageable);
        return addresses.map(addressMapper::toResponseDTO);
    }

    @Override
    public AddressResponseDTO updateByRollNumber(Integer rollNumber, AddressRequestDTO addressRequestDTO) {
        Student student = studentRepository.findByRollNumber(rollNumber).orElseThrow(()->new ResourceNotFoundException("student not found",rollNumber));
        Address address = student.getAddress();
        address.setCity(addressRequestDTO.getCity());
        address.setState(addressRequestDTO.getState());
        address.setPinCode(addressRequestDTO.getPinCode());
        Address updatedAddress = addressRepository.save(address);
        student = studentRepository.save(student);
        return addressMapper.toResponseDTO(student.getAddress());
    }


}
