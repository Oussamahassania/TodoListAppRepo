package com.oussama.TodoListApp.service.impl;

import com.oussama.TodoListApp.dto.UserDto;
import com.oussama.TodoListApp.exception.UserNotFoundException;
import com.oussama.TodoListApp.mapper.UserMapper;
import com.oussama.TodoListApp.model.User;
import com.oussama.TodoListApp.repository.UserRepository;
import com.oussama.TodoListApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;  // Inject PasswordEncoder instead of BCryptPasswordEncoder

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder; // Now Spring injects the PasswordEncoder bean
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        // Encrypt password before saving
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        // Convert UserDto to User entity
        User user = userMapper.userDtoToUser(userDto);
        // Save the user to the repository
        User userSaved = userRepository.save(user);
        // Return the saved user as a UserDto
        return userMapper.userToUserDto(userSaved);
    }

    @Override
    public UserDto updateUser(long id, UserDto userDto) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());

            // Only encode the password if it's provided
            if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }

            User updatedUser = userRepository.save(user);
            return userMapper.userToUserDto(updatedUser);
        }
        throw new UserNotFoundException("User not found with id " + id);  // Throw exception if user not found
    }

    @Override
    public UserDto getUserById(int id) {
        Optional<User> user = userRepository.findById((long)id);
        return user.map(userMapper::userToUserDto)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id)); // Throw exception if user not found
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(int id) {
        Optional<User> user = userRepository.findById((long)id);
        if (user.isPresent()) {
            userRepository.deleteById((long)id);
        } else {
            throw new UserNotFoundException("User not found with id " + id);  // Throw exception if user not found
        }
    }


    @Override
    public UserDto updateUserRole(long id, String role) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setRole(role);
            User updatedUser = userRepository.save(user);
            return userMapper.userToUserDto(updatedUser);
        } else {
            throw new UserNotFoundException("User not found with id " + id);  // Throw exception if user not found
        }
    }

    @Override
    public List<UserDto> getUsersByRole(String role) {
        List<User> users = userRepository.findByRole(role);
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found with role: " + role);
        } else {
            return users.stream()
                    .map(userMapper::userToUserDto)
                    .collect(Collectors.toList());
        }
    }
}
