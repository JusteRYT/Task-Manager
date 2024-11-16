package com.example.Task_Manager.repository;

import com.example.Task_Manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью `User`.
 * <p>
 * Предоставляет базовые CRUD-операции, а также дополнительные методы
 * для поиска пользователей по различным критериям.
 * </p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Находит пользователя по имени пользователя.
     *
     * @param username имя пользователя
     * @return пользователь, если найден
     */
    Optional<User> findByUsername(String username);
}
