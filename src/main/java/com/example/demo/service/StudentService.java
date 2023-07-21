package com.example.demo.service;

import com.example.demo.dto.IBaseMapper;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.service.interfaces.IStudentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentService extends BaseService<Student, UUID, StudentDTO> implements IStudentService {
    protected StudentService(JpaRepository<Student, UUID> repository, IBaseMapper<Student, StudentDTO> mapper) {
        super(repository, mapper);
    }
}
