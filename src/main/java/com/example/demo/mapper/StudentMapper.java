package com.example.demo.mapper;

import com.example.demo.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper {
    StudentMapper() {
    }

    public static Student toEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        String address = rs.getString("address");
        int classesId = rs.getInt("classes_id");
        return new Student(id, name, age, address, classesId);
    }
}
