package com.lea.mobile.dao.api;

import java.util.List;

public interface GenericDao<T> {
    void create(T entity);
    T read(int id);
    void update(T entity);
    void delete(T entity);
    List<T> selectAll();
}
