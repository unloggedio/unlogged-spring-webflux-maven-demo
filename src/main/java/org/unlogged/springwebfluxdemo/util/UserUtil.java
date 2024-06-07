package org.unlogged.springwebfluxdemo.util;


import org.springframework.stereotype.Component;
import org.unlogged.springwebfluxdemo.entity.User;
import org.unlogged.springwebfluxdemo.model.dto.UserDto;

@Component
public class UserUtil {

    public UserDto userEntityToUserDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getAge());
    }

    public User userDtoToUserEntity(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getAge());
    }
}

