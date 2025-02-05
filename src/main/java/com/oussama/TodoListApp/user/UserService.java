package com.oussama.TodoListApp.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
