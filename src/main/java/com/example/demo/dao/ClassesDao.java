package com.example.demo.dao;

import com.example.demo.connection.ConnectDB;
import com.example.demo.entity.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassesDao {
    private static ClassesDao instance = null;

    public static ClassesDao getInstance() {
        if (instance == null) {
            instance = new ClassesDao();
        }
        return instance;
    }

    public int create(Classes classes) {
        int result = 0;
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            return result;
        }
        try {
            String query = "insert into classes (name) values (?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, classes.getName());
            result = ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Classes classes) {
        int result = 0;
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            return result;
        }
        try {
            String query = "update classes set name = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, classes.getName());
            ps.setInt(2, classes.getId());
            result = ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Classes> getList() {
        List<Classes> listClasses = new ArrayList<>();
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            return listClasses;
        }
        try {
            String query = "select * from classes";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Classes classes = new Classes(id, name);
                listClasses.add(classes);
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listClasses;
    }

    public int delete(int id) {
        int result = 0;
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            return result;
        }
        String query = "delete from classes where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            result = ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Classes getById(int id) {
        Classes classes = null;
        Connection connection = ConnectDB.getConnection();
        if (connection == null) {
            return classes;
        }
        try {
            String query = "select * from classes where id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                classes = new Classes(id, name);
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }
}
