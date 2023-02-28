package ru.stomprf.repo;

import java.util.List;

public interface Dao<K extends Number, T> {

    List<T> findAll();

    T findEntityById(K id);

    T delete(K id);

    boolean delete(T entity);

    T save(T entity);

    T update(T entity);

}
