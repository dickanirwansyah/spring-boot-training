package com.demo.belajarspring.entity.generic.dao;

import java.util.List;

public interface GenericDAO<T> {

    T insert(T entity);
    void inserted(T entity);
    T update(T entity, Long id);
    void updated(T id, T entity);
    List<T> list();
    void delete(T entity);
    T getId(Long id);
}
