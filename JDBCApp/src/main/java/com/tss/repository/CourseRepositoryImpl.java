package com.tss.repository;

import com.tss.config.DBConnection;
import com.tss.entity.Course;
import com.tss.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryImpl implements CourseRepository{
    private Connection connection = DBConnection.connect();
    private List<Course> list;
    @Override
    public List<Course> readCourses() {
        try{
            list = new ArrayList<>();

            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from course");
            while(resultset.next()){
                list.add(new Course(resultset.getInt("course_id"),
                        resultset.getString("course_name"),
                        resultset.getDouble("fees")
                ));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void addNewCourse(Course course) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into course(course_id,course_name,fees) values (?,?,?)");
            statement.setInt(1,course.getCourseId());
            statement.setString(2,course.getName());
            statement.setDouble(3,course.getFees());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
