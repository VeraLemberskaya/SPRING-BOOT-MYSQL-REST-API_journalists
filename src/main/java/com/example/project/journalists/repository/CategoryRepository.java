package com.example.project.journalists.repository;

import com.example.project.journalists.exception.DaoException;
import com.example.project.journalists.model.entity.Category;

import java.util.Optional;

public interface CategoryRepository extends BaseRepository<Category> {
    Optional<Category> findByName(String categoryName) throws DaoException;
}
