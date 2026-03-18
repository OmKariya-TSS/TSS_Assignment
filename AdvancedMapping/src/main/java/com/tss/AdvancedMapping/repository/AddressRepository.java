package com.tss.AdvancedMapping.repository;

import com.tss.AdvancedMapping.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long> {
    Optional<Address> findByStudentId(long id);
    Page<Address> findByCity(String city, Pageable pageable);
    Boolean existsByCity(String city);
}
