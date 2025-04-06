package com.oussama.TodoListApp.mapper;

import com.oussama.TodoListApp.dto.TodoDto;
import com.oussama.TodoListApp.model.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TodoMapper {
   TodoMapper INSTANCE= Mappers.getMapper(TodoMapper.class);
   @Mapping(target = "categories",ignore = true)
    TodoDto todoToTodoDto(Todo todo);
   @Mapping(target = "categories",ignore = true)
    Todo TodoDtoToTodo(TodoDto tododto);
    List<TodoDto>todosToTodoDtos(List<Todo>todos);
    List<Todo>TodoDtosToTodos(List<TodoDto>tododtos);

}
