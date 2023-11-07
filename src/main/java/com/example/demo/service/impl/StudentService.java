package com.example.demo.service.impl;

import com.example.demo.entity.Classes;
import com.example.demo.entity.Students;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.ClassesRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.IStudentService;
import com.example.demo.service.dto.student.CreateStudentRequest;
import com.example.demo.service.dto.student.StudentDTO;
import com.example.demo.service.dto.student.UpdateStudentRequest;
import com.example.demo.service.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    
    private final StudentRepository studentRepository;
    private final ClassesRepository classesRepository;
    private final StudentMapper studentMapper;
    
    @Override
    public StudentDTO create(CreateStudentRequest request) {
        Set<Classes> classes = this.buildClassesList(request.getClassIds());
        
        Students students = this.studentMapper.toEntityForCreate(request, classes);
        
        students = this.studentRepository.save(students);
        
        return this.studentMapper.toDTO(students);
    }
    
    @Override
    public StudentDTO update(Long id, UpdateStudentRequest request) {
        Students students = this.studentRepository.findById(id).orElseThrow(
            RuntimeException::new
        );
        
        Set<Classes> classesSet = this.buildClassesList(request.getClassIds());
        
        students = this.studentMapper.toEntityForUpdate(students, request, classesSet);
        
        students = this.studentRepository.save(students);
        
        return this.studentMapper.toDTO(students);
    }
    
    @Override
    public StudentDTO findById(Long id) {
        return this.studentRepository.findById(id)
            .map(this.studentMapper::toDTO)
            .orElseThrow(
                () -> new BadRequestException("Student not found")
            );
    }
    
    private Set<Classes> buildClassesList(Set<Long> classIds) {
        Set<Classes> classesSet = new HashSet<>();
        classIds.forEach(id ->
            this.classesRepository.findById(id).ifPresent(
                classesSet::add
            )
        );
        return classesSet;
    }
}
