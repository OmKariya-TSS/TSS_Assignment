package com.tss.hibernate_demo.mapper;

import com.tss.hibernate_demo.dto.page.StudentResponsePageDTO;
import com.tss.hibernate_demo.dto.request.StudentRequestDTO;
import com.tss.hibernate_demo.dto.response.StudentResponseDTO;
import com.tss.hibernate_demo.entity.Student;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;


//public class StudentMapper {
//    public static Student studentRequestToStudentMapper(StudentRequestDTO dto){
//        Student student = new Student();
//        student.setAge(dto.getAge());
//        student.setName(dto.getName());
//        return student;
//    }
//    public static StudentResponseDTO studentToStudentResponseMapper(Student student){
//        return StudentResponseDTO.builder()
//                .studentId(student.getStudent_id())
//                .age(student.getAge())
//                .name(student.getName())
//                .build();
//    }
//}
@Mapper(componentModel = "spring")
public interface StudentMapper{
    Student toDto(StudentRequestDTO dto);
    StudentResponseDTO toResponseDTO(Student student);
    StudentResponsePageDTO toResponsePageDTO(Page<Student> Student);
}