package com.artech.api.services;

import java.util.Optional;

public interface BaseService<T, ID> {

    Optional<T> save(T entity);

    Optional<T> update(T entity, ID id);

    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    Iterable<T> findAll();

    Iterable<T> findAllById(Iterable<ID> ids);

    long count();

    void deleteById(ID id);

    void delete(T entity);

    void deleteAll(Iterable<? extends T> entities);

    void deleteAll();

}