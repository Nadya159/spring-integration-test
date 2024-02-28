package by.javaguru.spring.model.entity;

import lombok.*;

@Builder
@Data
@AllArgsConstructor     //добавлено для теста
public class User {
    Integer id;
    String username;
    String email;
}
