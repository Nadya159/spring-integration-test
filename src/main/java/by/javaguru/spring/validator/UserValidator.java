package by.javaguru.spring.validator;

import by.javaguru.spring.model.dto.UserCreateDto;

public class UserValidator {

    private static final UserValidator INSTANCE = new UserValidator();

    public ValidationResult isValid(UserCreateDto userDto) {
        var validationResult = new ValidationResult();
        if (userDto.username() == null)
            validationResult.add(Error.of("Username is invalid"));

        if (userDto.email() == null)
            validationResult.add(Error.of("Email is invalid"));
        return validationResult;
    }

    public static UserValidator getInstance(){
        return INSTANCE;
    }
}
