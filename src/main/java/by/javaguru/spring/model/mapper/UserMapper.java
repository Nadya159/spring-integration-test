package by.javaguru.spring.model.mapper;

import by.javaguru.spring.model.dto.UserCreateDto;
import by.javaguru.spring.model.entity.User;

public class UserMapper {
    public User mapFromDto(UserCreateDto userDto) {
        return User.builder()
                .username(userDto.username())
                .email(userDto.email())
                .build();
    }
}
