package com.oussama.TodoListApp.mapper;

import com.oussama.TodoListApp.dto.CategoryDto;
import com.oussama.TodoListApp.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE= Mappers.getMapper(CategoryMapper.class);
    @Mapping(target="user",source = "user")
    @Mapping(target="todos",ignore = true)
    CategoryDto categoryToCategoryDto(Category category);
    @Mapping(target="user",source = "user")
    Category categoryDtoToCategory(CategoryDto categoryDto);
    List<CategoryDto>categoriesToCategoryDtos(List<Category> categories);
    List<Category> categoryDtosToCategories(List<CategoryDto> categoryDtos);
}
