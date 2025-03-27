package com.oussama.TodoListApp.dto;

import com.oussama.TodoListApp.model.Category;
import com.oussama.TodoListApp.model.Todo;
import com.oussama.TodoListApp.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private long id;
    private String name;
    private String description;
    private UserDto user; // Changed from User to UserDto
    private List<TodoDto> todos;

    public static Category toEntity(CategoryDto categoryDto) {
        final Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setUser(UserDto.toEntity(categoryDto.getUser())); // Convert UserDto to User
        category.setTodos(
                categoryDto.getTodos() != null ? categoryDto.getTodos().stream().map(TodoDto::toEntity)
                        .collect(Collectors.toList()) : null
        );
        return category;
    }

    public static CategoryDto fromEntity(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .user(UserDto.fromEntity(category.getUser())) // Convert User to UserDto
                .todos(category.getTodos() != null ? category.getTodos().stream().map(TodoDto::fromEntity)
                        .collect(Collectors.toList()) : null)
                .build();
    }
}
