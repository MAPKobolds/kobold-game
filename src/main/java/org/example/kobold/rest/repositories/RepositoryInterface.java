package org.example.kobold.rest.repositories;

public interface RepositoryInterface<T> {
    T save(T entity);

    int deleteById(int id);

    T updateById(T entity, int id);

    T getById(int id);
}
