package com.oussama.TodoListApp.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "user_App")
public class User  {
    public User() {
    }

    public User(
                String fullName,
                String email,
                String password,
                LocalDateTime updatedAt,
                LocalDateTime createdAt,
                LocalDateTime deletedAt,
                AppUserRole userRole) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.userRole = userRole;
    }
    @Id
    @GeneratedValue
    private Integer id;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole userRole;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

}
