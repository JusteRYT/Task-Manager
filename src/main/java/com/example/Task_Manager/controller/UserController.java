package com.example.Task_Manager.controller;

import com.example.Task_Manager.model.User;
import com.example.Task_Manager.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Конструктор для внедрения зависимостей.
     *
     * @param userService сервис для работы с пользователями
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Создает нового пользователя.
     *
     * @param user объект пользователя
     * @return созданный пользователь
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        User createdUser = userService.createUser(user.getUsername(), encodedPassword);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Получает пользователя по имени пользователя.
     *
     * @param username имя пользователя
     * @return пользователь, если найден
     */
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
