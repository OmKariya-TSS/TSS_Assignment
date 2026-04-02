package com.tss.AdvancedMapping.service.implementations;

import com.tss.AdvancedMapping.dto.request.CourseRequestDTO;
import com.tss.AdvancedMapping.dto.response.CourseResponseDTO;
import com.tss.AdvancedMapping.dto.response.InstructorResponseDTO;
import com.tss.AdvancedMapping.dto.response.StudentResponseDTO;
import com.tss.AdvancedMapping.entity.Course;
import com.tss.AdvancedMapping.entity.Instructor;
import com.tss.AdvancedMapping.exception.BusinessRuleException;
import com.tss.AdvancedMapping.exception.ResourceNotFoundException;
import com.tss.AdvancedMapping.mapper.CourseMapper;
import com.tss.AdvancedMapping.mapper.InstructorMapper;
import com.tss.AdvancedMapping.mapper.StudentMapper;
import com.tss.AdvancedMapping.repository.CourseRepository;
import com.tss.AdvancedMapping.repository.InstructorRepository;
import com.tss.AdvancedMapping.service.interfaces.CourseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;
    private final StudentMapper studentMapper;
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
        if (course.getInstructor() != null) {
            throw new BusinessRuleException("Instructor already assigned");
        }
        course.setInstructor(instructor);
        if (instructor.getCourses() != null) {
            instructor.getCourses().add(course);
        }
        return courseMapper.toResponseDTO(course);
    }

    @Transactional
    @Override
    public InstructorResponseDTO assignCourseToInstructor(Long instructorId, Long courseId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found", instructorId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found", courseId));
        course.setInstructor(instructor);
        instructor.getCourses().add(course);
        return instructorMapper.toResponseDTO(instructor);
    }


    @Override
    public List<CourseResponseDTO> findAllCourseByInstructor(Long instructorId) {

        List<Course> courses = courseRepository.findCoursesByInstructorId(instructorId);

        if (courses == null || courses.isEmpty()) {
            throw new ResourceNotFoundException("Courses not found for instructor", instructorId);
        }

        return courseMapper.toResponseDTOList(courses);
    }

    @Override
    public InstructorResponseDTO findInstructorByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(()->new ResourceNotFoundException("Course not found", courseId));
        Instructor instructor = course.getInstructor();
        return instructorMapper.toResponseDTO(instructor);
    }

    @Override
    public Integer findCountOfCourses(Long instructorId) {
        Instructor found = instructorRepository.findById(instructorId).orElseThrow(()->new ResourceNotFoundException("Instructor not found", instructorId));
        return courseRepository.findCountOfCourses(found.getInstructorId());
    }

    @Override
    public Page<CourseResponseDTO> findCourseOfStudent(Long studentId, Pageable pageable) {
        return courseRepository.findCoursesByStudentId(studentId,pageable).map(courseMapper::toResponseDTO);
    }

    @Override
    public Page<StudentResponseDTO> findStudentOfCourse(Long courseId,Pageable pageable) {
        return courseRepository.findStudentsByCourseId(courseId,pageable).map(studentMapper::toResponseDTO);
    }




}
