package com.example.demo.dto.mapper;

public interface IBaseMapper<E, D, I> {
    E toEntity(D dto);
    E toEntity(D dto, I id);
    D toDto(E entity);
}
