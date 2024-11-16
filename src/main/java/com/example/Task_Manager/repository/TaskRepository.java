package com.example.Task_Manager.repository;

import com.example.Task_Manager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с сущностью `Task`.
 * <p>
 * Предоставляет базовые CRUD-операции, а также возможность добавления
 * дополнительных методов для запросов к базе данных.
 * </p>
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Находит все задачи для заданного пользователя.
     *
     * @param userId идентификатор пользователя
     * @return список задач, связанных с пользователем
     */
    List<Task> findByUserId(long userId);

    /**
     * Находит задачи с указанным статусом.
     *
     * @param status статус задачи (например, NEW, IN_PROGRESS, COMPLETED)
     * @return список задач с указанным статусом
     */
    List<Task> findByStatus(String status);
}
