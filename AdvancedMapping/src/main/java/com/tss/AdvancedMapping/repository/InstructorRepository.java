package com.tss.AdvancedMapping.repository;

import com.tss.AdvancedMapping.entity.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//read instructor whose name starts with a
//count number of course for instructor
@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    @Query("select i from Instructor i where i.name like :c%")
    Page<Instructor> findByNameStartsWithCharacter(@Param("c") Character c, Pageable pageable);

}
