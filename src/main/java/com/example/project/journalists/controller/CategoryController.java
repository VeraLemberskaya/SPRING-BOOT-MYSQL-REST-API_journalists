package com.example.project.journalists.controller;

import com.example.project.journalists.exception.ServiceException;
import com.example.project.journalists.model.dto.CategoryDto;
import com.example.project.journalists.model.entity.Category;
import com.example.project.journalists.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> getCategories() throws ServiceException{
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable("id") long id) throws ServiceException{
        return categoryService.getCategoryBYId(id);
    }

    @PostMapping
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) throws ServiceException{
        return categoryService.createCategory(categoryDto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable long id) throws ServiceException{
        return categoryService.deleteCategory(id);
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable long id, @RequestBody CategoryDto categoryDto) throws ServiceException{
        return categoryService.updateCategory(id, categoryDto);
    }
}
