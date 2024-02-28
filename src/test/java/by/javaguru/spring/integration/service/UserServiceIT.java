package by.javaguru.spring.integration.service;

import by.javaguru.spring.integration.annotation.IT;
import by.javaguru.spring.model.dto.UserCreateDto;
import by.javaguru.spring.model.dto.UserReadDto;
import by.javaguru.spring.service.UserService;
import by.javaguru.spring.model.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestConstructor;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

@IT
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UserServiceIT {
    private static final Integer USER_ID = 1;
    private static final String USER_NAME = "Test123";
    private static final String USER_EMAIL = "test123@gmail.com";

    @Autowired                              //или использовать @MockBean для заглушки
    private UserRepository userRepository;

    @Autowired
    private Connection connection;

    @Autowired
    private UserService userService;

    @Test
    void findById() {
        var actualResult = userService.findById(USER_ID);
        assertTrue(actualResult.isPresent());
        var expectedResult = new UserReadDto(USER_ID, USER_NAME, USER_EMAIL);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }

    @Test
    void create() {
        var newUserDto = new UserCreateDto("testTest", "testTest@gmail.com");
        userService.create(newUserDto);
        assertNotNull(newUserDto);
        assertNotNull(newUserDto.username());
        assertNotNull(newUserDto.email());
    }
}
