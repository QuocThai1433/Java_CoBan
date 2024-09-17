package com.example.demo.service;

import com.example.demo.dto.QueryStudentRequest;
import com.example.demo.dto.QueryStudentRequestV2;
import com.example.demo.dto.StudentDTO;
import com.example.demo.dto.mapper.IBaseMapper;
import com.example.demo.entity.Students;
import com.example.demo.repository.IStudentRepository;
import com.example.demo.repository.IStudentRepositoryCustom;
import com.example.demo.repository.StudentRepositoryIpl;
import com.example.demo.service.interfaces.IStudentService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService extends BaseService<Students, UUID, StudentDTO> implements IStudentService {
    public final IStudentRepository studentRepository;
    public final StudentRepositoryIpl studentRepositoryIpl;
    public final IStudentRepositoryCustom studentRepositoryCustom;

    public StudentService(JpaRepository<Students, UUID> repository,
                          IBaseMapper<Students, StudentDTO, UUID> mapper,
                          IStudentRepository studentRepository,
                          StudentRepositoryIpl studentRepositoryIpl,
                          IStudentRepositoryCustom studentRepositoryCustom) {
        super(repository, mapper);
        this.studentRepository = studentRepository;
        this.studentRepositoryIpl = studentRepositoryIpl;
        this.studentRepositoryCustom = studentRepositoryCustom;
    }


    public ResponseEntity<List<StudentDTO>> getListCountStudent(@RequestParam Integer count) {

        return ResponseEntity.ok(studentRepository.studentList(count)
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList()));
    }


    public ResponseEntity<List<StudentDTO>> getPaging(@RequestParam Integer page, @RequestParam Integer size) {

        Integer offset = page * size;
        return ResponseEntity.ok(studentRepository.studentPaging(size, offset)
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList()));
    }

    public ResponseEntity<List<StudentDTO>> getFilter(@RequestParam(required = false) String findName, @RequestParam(required = false) Integer age) {
//        var a=repository.studentFilter(findName,age);
        return ResponseEntity.ok(studentRepository.studentFilter(findName, age)
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList()));
    }

    public ResponseEntity<List<StudentDTO>> sortStudent(@RequestParam String nameSort, @RequestParam String nameType, @RequestParam String fullName, @RequestParam Integer age, @RequestParam Float score, @RequestParam String address) {
        return ResponseEntity.ok(studentRepositoryIpl.getByFilter(nameSort, nameType, fullName, age, score, address)
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList()));


    }

    public List<StudentDTO> query(@ParameterObject QueryStudentRequest request) {
        return this.studentRepositoryCustom.query(request).stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    public List<StudentDTO> query(@ParameterObject QueryStudentRequestV2 request, Pageable pageable) {
        return this.studentRepository.query(
                request.getFullName(),
                request.getAddress(),
                request.getScore(),
                request.getAge(),
                pageable
            ).stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }
}
