package com.example.project.journalists.controller;

import com.example.project.journalists.exception.ServiceException;
import com.example.project.journalists.model.dto.ArticleDto;
import com.example.project.journalists.service.ArticleService;
import com.example.project.journalists.service.impl.ArticleServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticlesController {

    private final ArticleService articleService;

    public ArticlesController(ArticleService articleService){
        this.articleService =articleService;
    }

    @GetMapping
    public List<ArticleDto> getArticles () throws ServiceException{
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public ArticleDto getArticleById(@PathVariable("id") long id) throws ServiceException{
        return  articleService.getArticleById(id);
    }

    @GetMapping(params = "category_id")
    public List<ArticleDto> getArticlesByCategory(@RequestParam("category_id") long id) throws ServiceException{
        return articleService.getArticlesByCategory(id);
    }

    @PostMapping
    public ArticleDto createArticle(@RequestBody ArticleDto articleDto) throws ServiceException{
        return articleService.createArticle(articleDto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteArticle(@PathVariable("id") long id) throws  ServiceException{
        return  articleService.deleteArticle(id);
    }

    @PutMapping("/{id}")
    public ArticleDto updateArticle(@PathVariable("id") long id, @RequestBody ArticleDto articleDto) throws ServiceException{
        return articleService.updateArticle(id,articleDto);
    }
}
