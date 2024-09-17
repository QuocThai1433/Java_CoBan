package com.example.demo.repository;

import com.example.demo.dto.QueryStudentRequest;
import com.example.demo.entity.Students;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IStudentRepositoryCustom {
    List<Students> getByFilter(Sort.Direction nameSortType);

    List<Students> query(QueryStudentRequest request);
}
