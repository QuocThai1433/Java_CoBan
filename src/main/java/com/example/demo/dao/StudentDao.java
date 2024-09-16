package com.example.demo.dao;

import com.example.demo.connection.ConnectDB;
import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private static StudentDao instance = null;

    public static StudentDao getInstance() {
        if (instance == null) {
            instance = new StudentDao();
        }
        return instance;
    }

    public List<Student> getList() {
        List<Student> studentList = new ArrayList<>();
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            return studentList;
        }
        try {
            String query = "select * from student";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                studentList.add(
                        StudentMapper.toEntity(rs)
                );
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public List<Student> getListByLimit(int limit) {
        List<Student> students = new ArrayList<>();
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            return students;
        }
        try {
            String query = "select * from student limit ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                students.add(
                        StudentMapper.toEntity(rs)
                );
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student getById(int id) {
        Student student = null;
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            return student;
        }
        try {
            String query = "select * from student where id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = StudentMapper.toEntity(rs);
            }
            ps.close();
            rs.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public int create(Student student) {
        int result = 0;
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            return result;
        }
        try {
            String query = "insert into student (name, age, address, classes_id) values (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getAddress());
            ps.setObject(4, student.getClassesId());
            result = ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Integer id, Student student) {
        int result = 0;
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            return result;
        }
        try {
            String query = "update student set name = ?, age = ?, address = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getAddress());
            ps.setInt(4, id);
            result = ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public int delete(int id) {
        int result = 0;
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            return result;
        }
        try {
            String query = " delete from srudent where id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            result = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public List<Student> getListByClasses(int ids) {
        List<Student> studentList = new ArrayList<>();

        String query = "SELECT * FROM ttsv t, classes c where c.id= t.classesId and t.classesId=?";
        try {
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setInt(1, ids);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                int classesId = rs.getInt("classesId");
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                int age = rs.getInt("age");
//                String address = rs.getString("address");
//                Student student = new Student(id, name, age, address, classesId);
//                studentList.add(student);
//            }
//            rs.close();
//            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

}
