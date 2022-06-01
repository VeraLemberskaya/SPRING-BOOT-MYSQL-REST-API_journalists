package com.example.project.journalists.service.impl;

import com.example.project.journalists.exception.AlreadyExistsException;
import com.example.project.journalists.exception.DaoException;
import com.example.project.journalists.exception.ResourceNotFoundException;
import com.example.project.journalists.exception.ServiceException;
import com.example.project.journalists.model.dto.CategoryDto;
import com.example.project.journalists.model.entity.Category;
import com.example.project.journalists.model.mapper.CategoryMapper;
import com.example.project.journalists.repository.impl.CategoryRepositoryImpl;
import com.example.project.journalists.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepositoryImpl categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepositoryImpl categoryRepository, CategoryMapper categoryMapper){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDto> getAllCategories() throws ServiceException {
        try{
            List<Category> categories = categoryRepository.findAll();
            List<CategoryDto> categoryDtos = new ArrayList<>();
            for(Category category: categories){
                CategoryDto categoryDto = categoryMapper.convertToDto(category);
                categoryDtos.add(categoryDto);
            }
            return categoryDtos;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public CategoryDto getCategoryBYId(long id) throws ServiceException {
        try{
            Optional<Category> optionalCategory = categoryRepository.findById(id);
            if(optionalCategory.isEmpty()){
                throw new ResourceNotFoundException("Category","id",id);
            }
             Category category = optionalCategory.get();
            return categoryMapper.convertToDto(category);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws ServiceException {
        try{
            Category category = categoryMapper.convertToEntity(categoryDto);
            Optional<Category> optionalCategory = categoryRepository.findByName(category.getName());
            if(optionalCategory.isPresent()){
                throw new AlreadyExistsException("Category","name",category.getName());
            }
            Category createdCategory =  categoryRepository.insert(category);
            return categoryMapper.convertToDto(createdCategory);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteCategory(long id) throws ServiceException {
        try{
            Optional<Category> optionalCategory = categoryRepository.findById(id);
            if(optionalCategory.isEmpty()){
                throw new ResourceNotFoundException("Category","id",id);
            }
            return categoryRepository.remove(id);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public CategoryDto updateCategory(long id, CategoryDto categoryDto) throws ServiceException {
        try{
            Optional<Category> optionalCategory = categoryRepository.findById(id);
            if(optionalCategory.isEmpty()){
                throw new ResourceNotFoundException("Category","id",id);
            }
            Category category = categoryMapper.convertToEntity(categoryDto);
            Category updatedCategory =  categoryRepository.update(id, category);
            return categoryMapper.convertToDto(updatedCategory);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
