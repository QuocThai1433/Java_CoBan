package com.example.demo.controller;

import com.example.demo.controller.interfaces.IBaseController;
import com.example.demo.service.interfaces.IBaseService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BaseController<T, I > implements IBaseController<T, I> {
    private final IBaseService<T, I> iBaseService;

    public BaseController(IBaseService<T, I> iBaseService) {
        this.iBaseService = iBaseService;
    }

    @Override
    public ResponseEntity<T> create(T entity) {
        return ResponseEntity.ok(iBaseService.create(entity));
    }

    @Override
    public ResponseEntity<T> update(T entity, I id) {
        return ResponseEntity.ok(iBaseService.update(entity, id));
    }

    @Override
    public ResponseEntity<T> getById(I id) {
        return ResponseEntity.ok(iBaseService.getById(id));
    }

    @Override
    public ResponseEntity<List<T>> getList() {
        return ResponseEntity.ok(iBaseService.getList());
    }

    @Override
    public ResponseEntity<Boolean> delete(I id) {
        return ResponseEntity.ok(iBaseService.delete(id));
    }




}
