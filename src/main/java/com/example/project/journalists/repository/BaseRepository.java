package com.example.project.journalists.repository;

import com.example.project.journalists.exception.DaoException;
import com.example.project.journalists.model.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<E extends BaseEntity> {
    public Optional<E> findById(long id) throws DaoException;
    public E insert(E e) throws DaoException;
    public boolean remove(long id) throws DaoException;
    public List<E> findAll() throws DaoException;
    public E update(long id, E e) throws DaoException;
}
