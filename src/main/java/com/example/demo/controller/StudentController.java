package com.example.demo.controller;

import com.example.demo.controller.interfaces.IStudentController;
import com.example.demo.dto.QueryStudentRequest;
import com.example.demo.dto.QueryStudentRequestV2;
import com.example.demo.dto.StudentDTO;
import com.example.demo.service.StudentService;
import com.example.demo.service.interfaces.IBaseService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class StudentController extends BaseController<StudentDTO, UUID> implements IStudentController {
    public final StudentService studentService;

    public StudentController(IBaseService<StudentDTO, UUID> iBaseService,
                             StudentService studentService) {
        super(iBaseService);
        this.studentService = studentService;
    }

    @GetMapping("getListCountStudent")
    public ResponseEntity<List<StudentDTO>> getListCountStudent(@RequestParam Integer count) {

        return studentService.getListCountStudent(count);
    }

    @GetMapping("getPaging")
    public ResponseEntity<List<StudentDTO>> getPaging(@RequestParam Integer page, @RequestParam Integer size) {

        return studentService.getPaging(page, size);
    }

    @GetMapping("getFilter")
    public ResponseEntity<List<StudentDTO>> getFilter(@RequestParam(required = false) String findName, @RequestParam(required = false) Integer age) {
//        var a=repository.studentFilter(findName,age);
        return studentService.getFilter(findName, age);
    }

    @GetMapping("sortStudent")
    public ResponseEntity<List<StudentDTO>> sortStudent(@RequestParam String nameSort, @RequestParam String nameType, @RequestParam(required = false) String fullName, @RequestParam(required = false) Integer age, @RequestParam(required = false) Float score, @RequestParam(required = false) String address) {
        return studentService.sortStudent(nameSort, nameType, fullName, age, score, address);
    }

    @GetMapping("query")
    public ResponseEntity<List<StudentDTO>> query(@ParameterObject QueryStudentRequest request) {
        return ResponseEntity.ok(this.studentService.query(request));
    }

    @GetMapping("v2/query")
    public ResponseEntity<List<StudentDTO>> queryV2(@ParameterObject QueryStudentRequestV2 request,
                                                    @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(this.studentService.query(request, pageable));
    }
}
