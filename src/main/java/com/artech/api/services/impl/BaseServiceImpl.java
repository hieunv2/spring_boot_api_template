package com.artech.api.services.impl;

import com.artech.api.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Author: quytm
 * Email : minhquylt95@gmail.com
 * Date  : Aug 11, 2019
 */

public class BaseServiceImpl<R extends CrudRepository<T, ID>, T, ID> {

    @Autowired
    protected R repo;

    public Optional<T> save(T entity) {
        entity = repo.save(entity);
        return Optional.ofNullable(entity);
    }

    public Optional<T> update(T data, ID id){
        Optional<T> entity  = repo.findById(id);
        if (!entity.isPresent()) throw new NotFoundException("Entity not found");
        BeanUtils.copyProperties(data,entity.get(), getNullPropertyNames(data));
        T newEntity = repo.save(entity.get());
        return Optional.ofNullable(newEntity);
    }

    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        return repo.saveAll(entities);
    }

    public Optional<T> findById(ID id) {
        return repo.findById(id);
    }

    public boolean existsById(ID id) {
        return repo.existsById(id);
    }

    public Iterable<T> findAll() {
        return repo.findAll();
    }

    public Iterable<T> findAllById(Iterable<ID> ids) {
        return repo.findAllById(ids);
    }

    public long count() {
        return repo.count();
    }

    public void deleteById(ID id) {
        repo.deleteById(id);
    }

    public void delete(T entity) {
        repo.delete(entity);
    }

    public void deleteAll(Iterable<? extends T> entities) {
        repo.deleteAll(entities);
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
            if(pd.getName().equals("id")) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}