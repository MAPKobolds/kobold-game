package org.uniba.kobold.rest;

public interface CRUDInterface<T> {
    T save(T entity);

    int deleteById(int id);

    T updateById(T entity, int id);

    T getById(int id);
}
