package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryStudentRequestV2 {
    private String fullName;
    private Integer age;
    private String address;
    private Integer score;
}
