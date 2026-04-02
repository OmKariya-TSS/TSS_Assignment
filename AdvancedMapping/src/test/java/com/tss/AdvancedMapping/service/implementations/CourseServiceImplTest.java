package com.tss.AdvancedMapping.service.implementations;

import com.tss.AdvancedMapping.dto.response.CourseResponseDTO;
import com.tss.AdvancedMapping.entity.Course;
import com.tss.AdvancedMapping.entity.Instructor;
import com.tss.AdvancedMapping.exception.ResourceNotFoundException;
import com.tss.AdvancedMapping.mapper.CourseMapper;
import com.tss.AdvancedMapping.mapper.InstructorMapper;
import com.tss.AdvancedMapping.repository.CourseRepository;
import com.tss.AdvancedMapping.repository.InstructorRepository;
import com.tss.AdvancedMapping.service.interfaces.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;l


@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private CourseMapper courseMapper;
    @Mock
    private InstructorMapper instructorMapper;

    @InjectMocks
    private CourseServiceImpl courseServiceImpl;

    @Test
    void findCountOfCourses_success() {
        Long instructorId = 1L;
        Instructor instructor = new Instructor();
        instructor.setInstructorId(instructorId);
        instructor.setName("Om Kariya");
        when(instructorRepository.findById(instructorId))
                .thenReturn(Optional.of(instructor));
        when(courseRepository.findCountOfCourses(instructorId))
                .thenReturn(2);
        Integer count = courseServiceImpl.findCountOfCourses(instructorId);
        assertNotNull(count);
        assertEquals(2, count);
        verify(instructorRepository, times(1)).findById(instructorId);
        verify(courseRepository, times(1)).findCountOfCourses(instructorId);
    }

    @Test
    void findAllCourseByInstructor_success() {

        Long instructorId = 1L;
        Course course1 = new Course();
        course1.setCourseId(1L);
        course1.setCourseName("Java");

        Course course2 = new Course();
        course2.setCourseId(2L);
        course2.setCourseName("AI");

        List<Course> courses = List.of(course1, course2);
        CourseResponseDTO dto1 = new CourseResponseDTO();
        dto1.setCourseName("Java");

        CourseResponseDTO dto2 = new CourseResponseDTO();
        dto2.setCourseName("AI");

        List<CourseResponseDTO> dtoList = List.of(dto1, dto2);
        when(courseRepository.findCoursesByInstructorId(instructorId))
                .thenReturn(courses);

        when(courseMapper.toResponseDTOList(courses))
                .thenReturn(dtoList);
        List<CourseResponseDTO> result =
                courseServiceImpl.findAllCourseByInstructor(instructorId);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Java", result.get(0).getCourseName());
        verify(courseRepository).findCoursesByInstructorId(instructorId);
        verify(courseMapper).toResponseDTOList(courses);
    }


    @Test
    void findAllCourseByInstructor_failure(){
        Long instructorId = 1L;
        when(courseRepository.findCoursesByInstructorId(instructorId))
                .thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> {
            courseServiceImpl.findAllCourseByInstructor(instructorId);
        });
        verify(courseRepository).findCoursesByInstructorId(instructorId);
    }

}