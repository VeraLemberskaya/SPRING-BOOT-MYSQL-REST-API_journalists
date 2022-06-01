package com.example.project.journalists.repository;

import com.example.project.journalists.exception.DaoException;
import com.example.project.journalists.model.entity.Article;

import java.util.List;

public interface ArticleRepository extends BaseRepository<Article> {
    List<Article> findByCategory(long categoryId) throws DaoException;
}
