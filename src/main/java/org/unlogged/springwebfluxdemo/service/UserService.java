package org.unlogged.springwebfluxdemo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.entity.User;
import org.unlogged.springwebfluxdemo.model.dto.UserDto;
import org.unlogged.springwebfluxdemo.repository.UserRepository;
import org.unlogged.springwebfluxdemo.util.UserUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserUtil userUtil;

    @Autowired
    public UserService(UserRepository userRepository, UserUtil userUtil) {
        this.userRepository = userRepository;
        this.userUtil = userUtil;
    }

    public Flux<UserDto> getAllUsers() {
        return userRepository.findAll().map(userUtil::userEntityToUserDto);
    }

    public Mono<UserDto> getUserById(String id) {
        return userRepository.findById(id).map(userUtil::userEntityToUserDto);
    }

    public Mono<UserDto> createUser(UserDto userDto) {
        User userEntity = userUtil.userDtoToUserEntity(userDto);
        return userRepository.save(userEntity)
                .map(userUtil::userEntityToUserDto);
    }

    public Mono<UserDto> updateUser(String id, UserDto userDto) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    BeanUtils.copyProperties(userDto, existingUser);
                    return userRepository.save(existingUser);
                })
                .map(userUtil::userEntityToUserDto);
    }

    public Mono<Void> deleteUser(String id) {
        return userRepository.deleteById(id);
    }
}

