package com.example.project.journalists.service;

import com.example.project.journalists.exception.ServiceException;
import com.example.project.journalists.model.dto.ArticleDto;

import java.util.List;

public interface ArticleService {
    List<ArticleDto> getAllArticles() throws ServiceException;
    List<ArticleDto> getArticlesByCategory (long categoryId) throws ServiceException;
    ArticleDto getArticleById(long id) throws ServiceException;
    ArticleDto createArticle(ArticleDto articleDto) throws ServiceException;
    boolean deleteArticle(long id) throws ServiceException;
    ArticleDto updateArticle(long id, ArticleDto articleDto) throws ServiceException;
}
