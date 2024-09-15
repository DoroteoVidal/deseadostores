package com.app.deseadostores.service;

import java.util.Set;

/**
 * Base service tiene todos los servicios basicos (CRUD).
 *
 *
 * @param <T> EntityResponse
 * @param <V> EntityRequest
 */
public interface BaseService<T, V> {

    T getById(Long id);

    Set<T> getAll();

    T save(V entityRequest);

    T update(Long id, V entityRequest);

    void delete(Long id);
}
