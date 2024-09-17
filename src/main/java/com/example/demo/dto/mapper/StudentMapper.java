package com.example.demo.dto.mapper;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Students;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StudentMapper implements IBaseMapper<Students, StudentDTO, UUID> {

    @Override
    public Students toEntity(StudentDTO dto) {
        return Students.builder()
                .id(dto.getId())
                .name(dto.getName())
                .age(dto.getAge())
                .address(dto.getAddress())
                .score(dto.getScore())
                .build();
    }

    @Override
    public Students toEntity(StudentDTO dto, UUID id) {
        return Students.builder()
                .id(id)
                .name(dto.getName())
                .age(dto.getAge())
                .address(dto.getAddress())
                .score(dto.getScore())
                .build();
    }

    @Override
    public StudentDTO toDto(Students entity) {
        return StudentDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .address(entity.getAddress())
                .score(entity.getScore())
                .build();
    }
}
