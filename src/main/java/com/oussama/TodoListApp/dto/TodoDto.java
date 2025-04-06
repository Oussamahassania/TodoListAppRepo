package com.oussama.TodoListApp.dto;

import com.oussama.TodoListApp.model.Category;
import com.oussama.TodoListApp.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto {
    private long id;
    private String title;
    private String description;
    private ZonedDateTime createdAt;
    private Boolean completed;
    private Boolean favorite;
    private CategoryDto category;
    private Integer categoryId;



    public static Todo toEntity(TodoDto todoDto) {
        final Todo todo = new Todo();
        todo.setId(todoDto.getId());
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.getCompleted());
        todo.setFavorite(todoDto.getFavorite());
        todo.setCreatedAt(todoDto.getCreatedAt() != null ? todoDto.getCreatedAt() : ZonedDateTime.now());
        todo.setCategory(CategoryDto.toEntity(todoDto.getCategory()));
        return todo;
    }

    public static TodoDto fromEntity(Todo todo) {
        return TodoDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .completed(todo.getCompleted())
                .favorite(todo.getFavorite())
                .createdAt(todo.getCreatedAt())
                .build();
    }


}
