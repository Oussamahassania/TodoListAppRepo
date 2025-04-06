package com.oussama.TodoListApp.service;

import com.oussama.TodoListApp.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(long id,CategoryDto categoryDto);
    Optional<CategoryDto> getCategoryByName(String name);
    CategoryDto getCategoryById(long id);
    List<CategoryDto> getCategoriesByUserId(long userId);
    void deleteCategory(long id);
}
