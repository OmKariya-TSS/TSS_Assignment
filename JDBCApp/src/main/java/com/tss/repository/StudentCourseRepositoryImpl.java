package com.tss.repository;

import com.tss.config.DBConnection;
import com.tss.entity.Course;
import com.tss.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseRepositoryImpl implements StudentCourseRepository{
    private Connection connection = DBConnection.connect();
    @Override
    public void assignCourse(int student_id, int course_id) {
        String sql = "insert into student_course(student_id,course_id) values(?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,student_id);
            ps.setInt(2,course_id);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Course> getCoursesOfStudent(int studentId) {
        List<Course> courses = new ArrayList<>();
        String sql = """
            select c.*
            from student_course sc
            join course c using(course_id)
            where sc.student_id = ?
            """;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("course_id"));
                course.setName(rs.getString("course_name"));
                course.setFees(rs.getDouble("fees"));
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public List<Student> getStudentsOfCourse(int courseId) {
        List<Student> students = new ArrayList<>();
        String sql = """
            select s.*
            from student_course sc
            join student s using(student_id)
            where sc.course_id = ?
            """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student();
                    student.setStudent_id(rs.getInt("student_id"));
                    student.setName(rs.getString("name"));
                    student.setAge(rs.getInt("age"));
                    student.setRoll_number(rs.getInt("roll_number"));
                    students.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
