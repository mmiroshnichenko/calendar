package com.calendar.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface BaseDAO <T> {
    T save(T object);

    T update(T object);

    void delete(T object);

    T findById(long id);
}
