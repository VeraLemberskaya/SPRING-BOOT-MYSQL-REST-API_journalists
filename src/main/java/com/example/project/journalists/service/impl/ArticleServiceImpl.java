package com.example.project.journalists.service.impl;

import com.example.project.journalists.exception.DaoException;
import com.example.project.journalists.exception.ResourceNotFoundException;
import com.example.project.journalists.exception.ServiceException;
import com.example.project.journalists.model.dto.ArticleDto;
import com.example.project.journalists.model.dto.CategoryDto;
import com.example.project.journalists.model.entity.Article;
import com.example.project.journalists.model.entity.Category;
import com.example.project.journalists.model.entity.Journalist;
import com.example.project.journalists.model.mapper.ArticleMapper;
import com.example.project.journalists.model.mapper.CategoryMapper;
import com.example.project.journalists.repository.ArticleRepository;
import com.example.project.journalists.repository.CategoryRepository;
import com.example.project.journalists.repository.JournalistRepository;
import com.example.project.journalists.service.ArticleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final JournalistRepository journalistRepository;

    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, CategoryRepository categoryRepository, JournalistRepository journalistRepository, ArticleMapper articleMapper){
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.journalistRepository = journalistRepository;
        this.articleMapper =articleMapper;
    }

    @Override
    public List<ArticleDto> getAllArticles() throws ServiceException {
        try{
            List<Article> articles = articleRepository.findAll();
            List<ArticleDto> articleDtos = new ArrayList<>();
            for(Article article:articles){
                ArticleDto articleDto = articleMapper.convertToDto(article);
                articleDtos.add(articleDto);
            }
            return articleDtos;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ArticleDto> getArticlesByCategory(long categoryId) throws ServiceException {
        try{
            List<Article> articles = articleRepository.findByCategory(categoryId);
            List<ArticleDto> articleDtos = new ArrayList<>();
            for(Article article:articles){
                ArticleDto articleDto = articleMapper.convertToDto(article);
                articleDtos.add(articleDto);
            }
            return articleDtos;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public ArticleDto getArticleById(long id) throws ServiceException {
        try{
            Optional<Article> optionalArticle = articleRepository.findById(id);
            if(optionalArticle.isEmpty()){
                throw new ResourceNotFoundException("Article","id",id);
            }
            return articleMapper.convertToDto(optionalArticle.get());
        } catch(DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public ArticleDto createArticle(ArticleDto articleDto) throws ServiceException {
        try{
            Article article = articleMapper.convertToEntity(articleDto);
            Optional<Journalist> optionalJournalist = journalistRepository.findById(article.getJournalist().getId());
            if(optionalJournalist.isEmpty()){
                throw new ResourceNotFoundException("Journalist","id",article.getJournalist().getId());
            }

            List<Category> categories = new ArrayList<>();
            for(Category category : article.getCategories()){
                Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
                if(optionalCategory.isEmpty()){
                    throw new ResourceNotFoundException("Category","id", category.getId());
                }
               categories.add(optionalCategory.get());
            }

            article.setJournalist(optionalJournalist.get());
            article.setCategories(categories);
            article.setDate(LocalDate.now());

            return articleMapper.convertToDto(articleRepository.insert(article));
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteArticle(long id) throws ServiceException {
        try{
            Optional<Article> optionalArticle = articleRepository.findById(id);
            if(optionalArticle.isEmpty()){
                throw new ResourceNotFoundException("Article","id",id);
            }
            return articleRepository.remove(id);
        } catch(DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public ArticleDto updateArticle(long id, ArticleDto articleDto) throws ServiceException {
        try{

            Optional<Article> optionalArticle = articleRepository.findById(id);
            if(optionalArticle.isEmpty()){
                throw new ResourceNotFoundException("Article", "id", id);
            }

            Article article = articleMapper.convertToEntity(articleDto);

            Optional<Journalist> optionalJournalist = journalistRepository.findById(article.getJournalist().getId());
            if(optionalJournalist.isEmpty()){
                throw new ResourceNotFoundException("Journalist","id",article.getJournalist().getId());
            }

            List<Category> categories = new ArrayList<>();
            for(Category category : article.getCategories()){
                Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
                if(optionalCategory.isEmpty()){
                    throw new ResourceNotFoundException("Category","id", category.getId());
                }
                categories.add(optionalCategory.get());
            }

            article.setJournalist(optionalJournalist.get());
            article.setCategories(categories);
            article.setDate(LocalDate.now());

            return articleMapper.convertToDto(articleRepository.update(id, article));
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
