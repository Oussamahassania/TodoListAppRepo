package com.oussama.TodoListApp.mapper;

import com.oussama.TodoListApp.dto.UserDto;
import com.oussama.TodoListApp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "categories", ignore = true)  // Ignoring categories as before
    @Mapping(target = "role", source = "role") // Explicitly map role field
    UserDto userToUserDto(User user);

    @Mapping(target = "categories", ignore = true)  // Ignoring categories as before
    @Mapping(target = "role", source = "role") // Explicitly map role field
    User userDtoToUser(UserDto userDto);

    List<UserDto> usersToUserDtos(List<User> users);

    List<User> userDtosToUsers(List<UserDto> usersDtos);
}
