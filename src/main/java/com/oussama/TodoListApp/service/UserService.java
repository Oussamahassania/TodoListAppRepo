package com.oussama.TodoListApp.service;

import com.oussama.TodoListApp.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(long id, UserDto userDto);
    UserDto getUserById(int id);
    List<UserDto> getAllUsers();
    void deleteUser(int id);

    // New Methods for Role Management
    UserDto updateUserRole(long id, String role);  // Update a user's role
    List<UserDto> getUsersByRole(String role);  // Fetch users by their role
}
