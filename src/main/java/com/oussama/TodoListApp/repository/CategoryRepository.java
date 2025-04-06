package com.oussama.TodoListApp.repository;

import com.oussama.TodoListApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
  Optional<Category>findByName(String name);
  void deleteById(long id);
  List<Category> findByUserId(long userId);
}
