package com.example.Task_Manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
/**
 * Класс `User` представляет пользователя в системе управления задачами.
 *
 * <p>Каждый пользователь имеет:
 * <ul>
 *   <li>Имя пользователя</li>
 *   <li>Пароль</li>
 *   <li>Список задач, связанных с ним</li>
 * </ul>
 * </p>
 */
@Entity
@Getter
@Setter
public class User {

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Уникальный идентификатор пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Имя пользователя (уникальное).
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * Пароль пользователя.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Список задач, связанных с пользователем.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public User() {

    }
}
