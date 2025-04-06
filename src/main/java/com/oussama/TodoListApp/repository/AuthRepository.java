package com.oussama.TodoListApp.repository;

import com.oussama.TodoListApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User,Integer>{
    Optional<User>findByEmail(String email);
}
