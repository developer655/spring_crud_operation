package com.example.spring_crud_operation.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString

@Document(collection = "users")
public class User {
    @Id
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    private UserType type;

    public User(String id, String name, String email, UserType type){
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
    }
}
