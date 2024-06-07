package org.unlogged.springwebfluxdemo.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.unlogged.springwebfluxdemo.entity.User;
import org.unlogged.springwebfluxdemo.model.dto.UserDto;

@Component
public class UserUtil {

    private final ModelMapper modelMapper;

    public UserUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDto userEntityToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User userDtoToUserEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}

