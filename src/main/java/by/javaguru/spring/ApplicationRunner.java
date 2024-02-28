package by.javaguru.spring;

import by.javaguru.spring.config.DatabaseProperties;
import by.javaguru.spring.model.dto.UserCreateDto;
import by.javaguru.spring.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {

    public static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunner.class, args);
        System.out.println(context.getBean(DatabaseProperties.class));

        var userService = context.getBean("userService", UserService.class);
        var user = userService.findById(2);
        System.out.println(user);
        //userService.create(new UserCreateDto(null, "test@gmail.com"));               //для проверки валидации на null
        userService.create(new UserCreateDto("testCreate", "test@gmail.com"));
    }
}
