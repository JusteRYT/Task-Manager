package com.example.Task_Manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
/*
Класс сущность для задач
 */
@Entity
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDateTime dueDate;

    @ManyToOne
    private User user;
}
