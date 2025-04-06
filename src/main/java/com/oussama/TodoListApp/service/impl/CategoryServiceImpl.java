package com.oussama.TodoListApp.service.impl;

import com.oussama.TodoListApp.dto.CategoryDto;
import com.oussama.TodoListApp.mapper.CategoryMapper;
import com.oussama.TodoListApp.model.Category;
import com.oussama.TodoListApp.repository.CategoryRepository;
import com.oussama.TodoListApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.categoryDtoToCategory(categoryDto);
        Category categorySaved=categoryRepository.save(category);
        return categoryMapper.categoryToCategoryDto(categorySaved);
    }

    @Override
    public CategoryDto updateCategory(long id, CategoryDto categoryDto) {
        if (categoryRepository.existsById((int)id)){
           categoryDto.setId(id);
           Category category=categoryMapper.categoryDtoToCategory(categoryDto);
           Category categorySaved=categoryRepository.save(category);
           return categoryMapper.categoryToCategoryDto(categorySaved);
        }
        return null;
    }

    @Override
    public Optional<CategoryDto> getCategoryByName(String name) {
        return categoryRepository
                .findByName(name)
                .map(categoryMapper::categoryToCategoryDto);
    }

    @Override
    public CategoryDto getCategoryById(long id) {
        return categoryRepository.findById((int)id)
                .map(categoryMapper::categoryToCategoryDto)
                .orElse(null);
    }

    @Override
    public List<CategoryDto> getCategoriesByUserId(long userId) {
        List<Category> categories = categoryRepository.findByUserId(userId);
        return categories.stream()
                .map(categoryMapper::categoryToCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(long id) {
      categoryRepository.deleteById(id);
    }
}
