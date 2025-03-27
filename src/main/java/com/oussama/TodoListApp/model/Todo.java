package com.oussama.TodoListApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Todo {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String description;
    private ZonedDateTime createdAt;
    private Boolean completed;
    private Boolean favorite;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonIgnore
    private Category category;
}
