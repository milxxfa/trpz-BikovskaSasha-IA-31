package org.audioeditor.repository;

import java.util.List;

/**
 * Прості CRUD-операції для репозиторіїв.
 */
public interface IRepository<T> {
    List<T> getAll();
    T getById(int id);
    void add(T item);
    void update(T item);
    void delete(int id);
}
