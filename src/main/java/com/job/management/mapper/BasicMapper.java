package com.job.management.mapper;

public interface BasicMapper <E, D> {
    E toEntity(D dto);
    D toDTO(E entity);
}
