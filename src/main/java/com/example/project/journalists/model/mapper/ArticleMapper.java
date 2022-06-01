package com.example.project.journalists.model.mapper;

import com.example.project.journalists.model.dto.ArticleDto;
import com.example.project.journalists.model.entity.Article;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ArticleMapper implements BaseMapper<ArticleDto, Article> {

    private final JournalistMapper journalistMapper;
    private final CategoryMapper categoryMapper;

    public ArticleMapper(JournalistMapper journalistMapper, CategoryMapper categoryMapper){
        this.journalistMapper = journalistMapper;
        this.categoryMapper = categoryMapper;
    }
    @Override
    public ArticleDto convertToDto(Article article) {
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .date(article.getDate())
                .journalist(journalistMapper.convertToDto(article.getJournalist()))
                .categories(article.getCategories().stream().map(category -> categoryMapper.convertToDto(category)).collect(Collectors.toList()))
                .build();
    }

    @Override
    public Article convertToEntity(ArticleDto articleDto) {
        return Article.builder()
                .id(articleDto.getId())
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .date(articleDto.getDate())
                .journalist(journalistMapper.convertToEntity(articleDto.getJournalist()))
                .categories(articleDto.getCategories().stream().map(categoryDto -> categoryMapper.convertToEntity(categoryDto)).collect(Collectors.toList()))
                .build();
    }
}
