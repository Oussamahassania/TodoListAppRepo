package com.oussama.TodoListApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oussama.TodoListApp.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private long id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;

    @JsonIgnore
    private List<CategoryDto> categories;

    public static User toEntity(UserDto userDto) {
        final User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCategories(
                userDto.getCategories() != null ? userDto.getCategories().stream().map(CategoryDto::toEntity)
                        .collect(Collectors.toList()) : null
        );
        return user;
    }

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .categories(user.getCategories() != null ? user.getCategories().stream().map(CategoryDto::fromEntity)
                        .collect(Collectors.toList()) : null)
                .build();
    }
}
