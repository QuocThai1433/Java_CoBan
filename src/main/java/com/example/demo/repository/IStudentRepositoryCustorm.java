package com.example.demo.repository;

import com.example.demo.entity.Students;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStudentRepositoryCustorm {
    List<Students> getByFilter (String nameSort,String nameType, @Param("name") String fullName, @Param("age") Integer age,@Param("score ") Float score,@Param("address") String address );
}
