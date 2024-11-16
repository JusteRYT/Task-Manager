package com.example.Task_Manager.service;

import com.example.Task_Manager.model.User;
import com.example.Task_Manager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Сервис для работы с пользователями (`User`).
 *
 * <p>Обеспечивает бизнес-логику для операций над пользователями,
 * включая поиск, регистрацию и авторизацию.</p>
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Конструктор для внедрения зависимостей.
     *
     * @param userRepository репозиторий для работы с пользователями
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Находит пользователя по имени пользователя.
     *
     * @param username имя пользователя
     * @return объект пользователя, если найден
     */
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Создает нового пользователя.
     *
     * @param user объект пользователя
     * @return сохраненный пользователь
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
