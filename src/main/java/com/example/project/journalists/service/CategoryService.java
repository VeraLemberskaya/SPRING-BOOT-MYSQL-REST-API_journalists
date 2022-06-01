package com.example.project.journalists.service;

import com.example.project.journalists.exception.ServiceException;
import com.example.project.journalists.model.dto.CategoryDto;
import com.example.project.journalists.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories() throws ServiceException;
    CategoryDto getCategoryBYId(long id) throws ServiceException;
    CategoryDto createCategory(CategoryDto categoryDto) throws ServiceException;
    boolean deleteCategory(long id) throws ServiceException;
    CategoryDto updateCategory(long id, CategoryDto categoryDto) throws ServiceException;
}
