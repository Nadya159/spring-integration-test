package by.javaguru.spring.model.entity;

import lombok.*;

@Builder
@Data
public class User {
    Integer id;
    String username;
    String email;
}
