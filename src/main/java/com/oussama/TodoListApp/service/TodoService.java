package com.oussama.TodoListApp.service;

import com.oussama.TodoListApp.dto.TodoDto;
import java.util.List;
import java.util.Optional;

public interface TodoService {
    TodoDto createTodo(TodoDto todoDto);
    TodoDto updateTodo(long id, TodoDto todoDto);
    Optional<TodoDto> getTodoByName(String name);

    List<TodoDto> getTodosByCategoryId(long categoryId);
    List<TodoDto> getCompletedTodos(); // Removed parameter, better for filtering
    List<TodoDto> getUncompletedTodos();
    List<TodoDto> getFavoriteTodos(); // Renamed to plural for clarity

    void deleteById(long id);

    // ✅ New Methods (Recommended)
    Optional<TodoDto> getTodoById(long id);  // Fetch todo by ID
    List<TodoDto> getAllTodos();  // Fetch all todos
    List<TodoDto>getTodayTodos();
}
