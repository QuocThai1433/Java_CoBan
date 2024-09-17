package com.example.demo.service.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBaseService<D, I> {
    D create(D entity);

    D update(D entity, I id);

    D getById(I id);

    List<D> getList();

    boolean delete(I id);



}
