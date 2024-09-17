package com.example.demo.controller;

import com.example.demo.controller.interfaces.IClassesController;
import com.example.demo.dto.ClassesDTO;
import com.example.demo.service.interfaces.IBaseService;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ClassesController extends BaseController<ClassesDTO, UUID> implements IClassesController {
    public ClassesController(IBaseService<ClassesDTO, UUID> idIBaseService) {
        super(idIBaseService);
    }


}
