package com.tss.AdvancedMapping.service.implementations;

import com.tss.AdvancedMapping.dto.request.StudentRequestDTO;
import com.tss.AdvancedMapping.dto.response.StudentResponseDTO;
import com.tss.AdvancedMapping.entity.Course;
import com.tss.AdvancedMapping.entity.Student;
import com.tss.AdvancedMapping.exception.ResourceNotFoundException;
import com.tss.AdvancedMapping.mapper.StudentMapper;
import com.tss.AdvancedMapping.repository.CourseRepository;
import com.tss.AdvancedMapping.repository.StudentRepository;
import com.tss.AdvancedMapping.service.interfaces.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final CourseRepository courseRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public StudentResponseDTO saveStudent(StudentRequestDTO student) {
        Student result = studentMapper.toDto(student);
        Student savedStudent = studentRepository.save(result);
        logger.info("Saving the student");
        return studentMapper.toResponseDTO(savedStudent);
    }

    @Override
    public Page<StudentResponseDTO> findAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable).map(studentMapper::toResponseDTO);
    }

    @Override
    public StudentResponseDTO findStudentById(long id) {
        if(!studentRepository.existsById(id)){
            throw new ResourceNotFoundException("student not found",id);
        }
        logger.info("finding students");
        return  studentMapper.toResponseDTO(studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("student not found",id)));
    }

    @Override
    public void deleteStudent(long id) {
        logger.info("Student deleting");
        studentRepository.deleteById(id);
    }

    @Override
    public Page<StudentResponseDTO> findByCity(String city,Pageable pageable) {
        if(!studentRepository.existsByAddressCity(city)){
            throw new ResourceNotFoundException("student not found",city);
        }
        logger.info("finding all students");
        return studentRepository.findByAddressCity(city,pageable).map(studentMapper::toResponseDTO);
    }

    @Override
    public StudentResponseDTO findByRollNumber(Integer rollNumber) {
        logger.info("finding student by roll number");
        return studentMapper.toResponseDTO(studentRepository.findByRollNumber(rollNumber).orElseThrow(()->new ResourceNotFoundException("student not found",rollNumber)));
    }

    @Override
    @Transactional
    public StudentResponseDTO assignCourseToStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("student not found",studentId));
        Course course = courseRepository.findById(courseId).orElseThrow(()->new ResourceNotFoundException("student not found",courseId));
        student.getCourses().add(course);
        course.getStudents().add(student);
        return studentMapper.toResponseDTO(student);
    }

    @Override
    public StudentResponseDTO updateCourse(Long oldCourseId, Long studentId,Long newCourseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("student not found",studentId));
        Course oldCourse = courseRepository.findById(oldCourseId).orElseThrow(()->new ResourceNotFoundException("student not found",oldCourseId));
        Course newCourse = courseRepository.findById(newCourseId).orElseThrow(()->new ResourceNotFoundException("student not found",newCourseId));
        if(!student.getCourses().contains(oldCourse)){
            throw new ResourceNotFoundException("course not opted",oldCourseId);
        }
        else {
            student.getCourses().remove(oldCourse);
            oldCourse.getStudents().remove(student);
            student.getCourses().add(newCourse);
            newCourse.getStudents().add(student);
        }
        return studentMapper.toResponseDTO(studentRepository.save(student));
    }
}
