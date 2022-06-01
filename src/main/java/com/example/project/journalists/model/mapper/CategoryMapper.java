package com.example.project.journalists.model.mapper;

import com.example.project.journalists.model.dto.CategoryDto;
import com.example.project.journalists.model.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements BaseMapper<CategoryDto, Category>{
    @Override
    public CategoryDto convertToDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Override
    public Category convertToEntity(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }
}
