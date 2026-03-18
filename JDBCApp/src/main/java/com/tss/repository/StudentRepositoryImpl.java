package com.tss.repository;


import com.tss.config.DBConnection;
import com.tss.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository{
    private Connection connection = DBConnection.connect();
    private List<Student> list;
    @Override
    public List<Student> readStudents() {
        try{
            list = new ArrayList<>();

            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from student");
            while(resultset.next()){
                list.add(new Student(resultset.getInt("student_id"),
                        resultset.getInt("roll_number"),
                        resultset.getInt("age"),
                        resultset.getString("name")
                )) ;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void addNewStudent(Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into student(student_id,name,roll_number,age) values (?,?,?,?)");
            statement.setInt(1,student.getStudent_id());
            statement.setString(2,student.getName());
            statement.setInt(3,student.getRoll_number());
            statement.setInt(4,student.getAge());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
