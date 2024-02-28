package by.javaguru.spring.service;

import by.javaguru.spring.model.dto.UserReadDto;
import by.javaguru.spring.model.entity.User;
import by.javaguru.spring.model.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private static final Integer USER_ID = 1;
    private static final String USER_NAME = "Test123";
    private static final String USER_EMAIL = "test123@gmail.com";
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Поиск User по ID")
    void findById() {
        Mockito.doReturn(Optional.of(new User(USER_ID, USER_NAME, USER_EMAIL)))
                .when(userRepository).findById(USER_ID);
        var actualResult = userService.findById(USER_ID);
        Assertions.assertTrue(actualResult.isPresent());
        var expectedResult = new UserReadDto(USER_ID, USER_NAME, USER_EMAIL);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }
}
