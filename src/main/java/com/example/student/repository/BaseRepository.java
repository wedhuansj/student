package com.example.student.repository;

import java.util.List;

public interface BaseRepository<T,ID> {
    void add(T entity);
    void update(T entity);
    void delete(ID id,Class<T> cls);
    T findById(ID id,Class<T> cls);
    List<T> findAll(Class<T> cls);
}