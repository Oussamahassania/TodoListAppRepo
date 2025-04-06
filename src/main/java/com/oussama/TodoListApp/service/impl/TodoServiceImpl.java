package com.oussama.TodoListApp.service.impl;

import com.oussama.TodoListApp.dto.TodoDto;
import com.oussama.TodoListApp.exception.TodoNotFoundException;
import com.oussama.TodoListApp.mapper.TodoMapper;
import com.oussama.TodoListApp.model.Todo;
import com.oussama.TodoListApp.repository.CategoryRepository;
import com.oussama.TodoListApp.repository.TodoRepository;
import com.oussama.TodoListApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todorepository;
    private final TodoMapper todomapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todorepository, TodoMapper todomapper, CategoryRepository categoryRepository) {
        this.todorepository = todorepository;
        this.todomapper = todomapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public TodoDto createTodo(TodoDto tododto) {
        Todo todo=todomapper.TodoDtoToTodo(tododto);
        Todo todoSaved=todorepository.save(todo);
        return todomapper.todoToTodoDto(todoSaved);
    }

    @Override
    public TodoDto updateTodo(long id, TodoDto tododto) {
       Optional<Todo>existingTodo=todorepository.findById((int)id);
       if(existingTodo.isPresent()){
           Todo todo=existingTodo.get();
           todo.setTitle(tododto.getTitle());
           todo.setDescription(tododto.getDescription());
           todo.setFavorite(tododto.getFavorite());
           todo.setCompleted(tododto.getCompleted());
           if (tododto.getCategoryId()!=null){
               categoryRepository.findById(tododto.getCategoryId())
                       .ifPresent(todo::setCategory);
           }
           Todo updateTodo=todorepository.save(todo);
           return todomapper.todoToTodoDto(updateTodo);
       }
       else{
           throw new TodoNotFoundException("Todo with id "+ id +" not found");
       }

    }


//return todo by name (each todo name is unique)
    @Override
    public Optional<TodoDto> getTodoByName(String name) {
        Optional<Todo>todo=todorepository.findByTitle(name);
        return todo.map(todomapper::todoToTodoDto);
    }

    @Override
    public List<TodoDto> getTodosByCategoryId(long id) {
        List<Todo>todos=todorepository.findByCategoryId((int)id);
        if (todos.isEmpty()){
            throw new TodoNotFoundException("no todos found for category that id " +id);
        }
        else{
        return  todos.stream()
                .map(todomapper::todoToTodoDto)
                .collect(Collectors.toList());
        }
    }

    @Override
    public List<TodoDto> getCompletedTodos() {
        List<Todo>completedTodos=todorepository.findByCompleted(true);
        if (completedTodos.isEmpty()){
            throw new TodoNotFoundException("no completed todos founded");
        }else{
            return  completedTodos.stream()
                    .map(todomapper::todoToTodoDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<TodoDto> getUncompletedTodos() {
        List<Todo>uncompletedTodos=todorepository.findByCompleted(false);
        if (uncompletedTodos.isEmpty()){
            throw new TodoNotFoundException("no uncompleted todos founded");
        }else{
           return   uncompletedTodos.stream()
                   .map(todomapper::todoToTodoDto)
                   .collect(Collectors.toList());
        }
    }

    @Override
    public List<TodoDto> getFavoriteTodos() {
        List<Todo>favoriteTodos=todorepository.findByFavorite(true);
        if (favoriteTodos.isEmpty()){
            throw new TodoNotFoundException("no favorite todos founded");
        }else{
            return  favoriteTodos.stream()
                    .map(todomapper::todoToTodoDto)
                    .collect(Collectors.toList());
        }
    }


    @Override
    public void deleteById(long id) {
    if(!todorepository.existsById((int)id)){
      throw new TodoNotFoundException("todo with id "+id+" doesn't exist");
    }else{
        todorepository.deleteById((int)id);
    }
    }

    @Override
    public Optional<TodoDto> getTodoById(long id) {
        Optional<Todo>existTodos=todorepository.findById((int)id);
        if (existTodos.isPresent()){
            return Optional.of(todomapper.todoToTodoDto(existTodos.get()));
        }else{
            throw new TodoNotFoundException("todo with id "+id+" not founded");
        }
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo>allTodos=todorepository.findAll();
        if (allTodos.isEmpty()){
            throw new TodoNotFoundException("no todos founded");
        }else{
            return  allTodos.stream()
                    .map(todomapper::todoToTodoDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<TodoDto> getTodayTodos() {
        LocalDate today=LocalDate.now();
        List<Todo>todos=todorepository.findAll();
        List<Todo>todayTodos=todos.stream()
                .filter(todo ->
                        todo.getCreatedAt()!=null &&
                                todo.getCreatedAt().toLocalDate().isEqual(today))
                .collect(Collectors.toList());
        return todayTodos.stream().map(todomapper::todoToTodoDto).collect(Collectors.toList());
    }
}
