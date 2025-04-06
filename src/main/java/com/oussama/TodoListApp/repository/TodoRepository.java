package com.oussama.TodoListApp.repository;

import com.oussama.TodoListApp.model.Category;
import com.oussama.TodoListApp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
    List<Todo>findByCategoryId(long categoryId);
    List<Todo>findByCompleted(boolean completed);
    List<Todo> findByFavorite(Boolean favorite);

    Optional<Todo> findByTitle(String title);
}
