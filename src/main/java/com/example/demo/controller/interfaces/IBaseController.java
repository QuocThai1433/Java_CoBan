package com.example.demo.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IBaseController<T, I> {
    @PostMapping("create")
    ResponseEntity<T> create(@RequestBody T entity);

    @PutMapping("update")
    ResponseEntity<T> update(@RequestBody T entity, @RequestParam I id);

    @GetMapping("{id}/detail")
    ResponseEntity<T> getById(@PathVariable I id);

    @GetMapping("getList")
    ResponseEntity<List<T>> getList();

    @DeleteMapping("{id}/delete")
    ResponseEntity<Boolean> delete(@PathVariable I id);



}
