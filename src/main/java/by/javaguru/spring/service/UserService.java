package by.javaguru.spring.service;

import by.javaguru.spring.model.dto.UserCreateDto;
import by.javaguru.spring.model.dto.UserReadDto;
import by.javaguru.spring.model.exception.ValidationException;
import by.javaguru.spring.model.mapper.UserMapper;
import by.javaguru.spring.model.repository.UserRepository;
import by.javaguru.spring.validator.UserValidator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserValidator userValidator = UserValidator.getInstance();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserReadDto> findById(Integer id) {
        return userRepository.findById(id)
                .map(entity -> new UserReadDto(entity.getId(), entity.getUsername(), entity.getEmail()));
    }

    public void create(UserCreateDto userCreateDto) {
        var validationResult = userValidator.isValid(userCreateDto);
        if (!validationResult.isValid()) {
            throw new ValidationException("User hasn't been created");
        } else {
            var entity = new UserMapper().mapFromDto(userCreateDto);
            userRepository.save(entity);
        }
    }
}


