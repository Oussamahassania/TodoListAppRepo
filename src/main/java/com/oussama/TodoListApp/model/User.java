package com.oussama.TodoListApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String email;
    @JsonIgnore
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Category> categories;
}
