package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QueryStudentRequest {
    private String fullName;
    private String age;
    private String address;
    private String score;
    private List<String> sorts;
}
