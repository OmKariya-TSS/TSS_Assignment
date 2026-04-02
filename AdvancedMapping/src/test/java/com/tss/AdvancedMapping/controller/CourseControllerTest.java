package com.tss.AdvancedMapping.controller;

import com.tss.AdvancedMapping.dto.response.CourseResponseDTO;
import com.tss.AdvancedMapping.entity.Instructor;
import com.tss.AdvancedMapping.service.interfaces.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private CourseService courseService;

    @Test
    void testGetAllCourses() throws Exception {
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setCourseName("Java");
        courseResponseDTO.setInstructorName("Om Kariya");
        CourseResponseDTO courseResponseDTO1 = new CourseResponseDTO();
        courseResponseDTO1.setCourseName("Spring boot");
        courseResponseDTO1.setInstructorName("Om Kariya");
        List<CourseResponseDTO> courseList = List.of(
                courseResponseDTO,
                courseResponseDTO1
        );
        when(courseService.findAllCourseByInstructor(1L))
                .thenReturn(courseList);
        mockMvc.perform(get("/courses/getAllCourses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].courseName").value("Java"))
                .andExpect(jsonPath("$[1].courseName").value("Spring boot"));
    }
}