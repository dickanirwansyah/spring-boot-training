package com.demo.belajarspring.entity.generic.service;

import java.util.List;

public interface GenericService<T> {

    boolean inserted(T data);
    List<T> list();
    T getId(Long id);
}
