package com.oussama.TodoListApp.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService  {
private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String userSigneUp(User user) {
        userRepository.save(user);
        return "userSigneIn";
    }

    public String userSigneIn(String email, String password) {
        Optional<User> userOptional =userRepository.findByEmail(email);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            if(user.getPassword().equals(password)) {
                return "index";
            }
        }
       return null;
    }
}
