package com.tss.AdvancedMapping.service;

import com.tss.AdvancedMapping.dto.CourseRequestDTO;
import com.tss.AdvancedMapping.dto.CourseResponseDTO;
import com.tss.AdvancedMapping.dto.InstructorResponseDTO;
import com.tss.AdvancedMapping.entity.Course;
import com.tss.AdvancedMapping.entity.Instructor;
import com.tss.AdvancedMapping.exception.ResourceNotFoundException;
import com.tss.AdvancedMapping.mapper.CourseMapper;
import com.tss.AdvancedMapping.mapper.InstuctorMapper;
import com.tss.AdvancedMapping.repository.CourseRepository;
import com.tss.AdvancedMapping.repository.InstructorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements  CourseService {
    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final InstuctorMapper instructorMapper;
    @Override
    public CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO) {
        Course course = courseMapper.toDTO(courseRequestDTO);
        return courseMapper.toResponseDTO(courseRepository.save(course));
    }

    @Override
    public CourseResponseDTO findById(long id) {
        return courseMapper.toResponseDTO(courseRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("course not found",id)));
    }

    @Override
    public Page<CourseResponseDTO> findAll(Pageable pageable) {
        return  courseRepository.findAll(pageable).map(courseMapper::toResponseDTO);
    }


    @Transactional
    @Override
    public CourseResponseDTO assignInstructorToCourse(Long instructorId, Long courseId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found", instructorId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found", courseId));
        course.setInstructor(instructor);
        if (instructor.getCourses() != null) {
            instructor.getCourses().add(course);

        }
        courseRepository.save(course);
        Course updatedCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found", courseId));

        return  courseMapper.toResponseDTO(course);
    }

    @Override
    public InstructorResponseDTO assignCourseToInstructor(Long instructorId, Long courseId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new ResourceNotFoundException("Instructor not found", instructorId));
        Course course = courseRepository.findById(courseId).orElseThrow(()->new ResourceNotFoundException("Course not found", courseId));
        instructor.getCourses().add(course);
        Instructor instructor1 = instructorRepository.save(instructor);
        return instructorMapper.toResponseDTO(instructor1);
    }
}
