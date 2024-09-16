package com.example.demo.entity;

public class Student {

    private Integer id;

    private String name;

    private Integer age;

    private String address;

    private Integer classesId;

    public String toString() {
        return "id = " + id + ", fullName = " + name + ", age = " + age + ", address = " + address + ", classesId = " + classesId;
    }

    public Student(Integer id, String name, Integer age, String address, Integer classesId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.classesId = classesId;
    }

    public Student(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getClassesId() {
        return classesId;
    }

    public void setClassesId(int classesId) {
        this.classesId = classesId;
    }
}
