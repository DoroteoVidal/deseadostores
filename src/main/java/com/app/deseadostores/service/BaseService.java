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

    T getById(Long id) throws Exception;

    Set<T> getAll() throws Exception;

    T save(V entityRequest) throws Exception;

    T update(Long id, V entityRequest) throws Exception;

    void delete(Long id) throws Exception;
}
