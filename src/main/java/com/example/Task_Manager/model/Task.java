package com.example.Task_Manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Класс `Task` представляет задачу в системе управления задачами.
 *
 * <p>Каждая задача содержит:
 * <ul>
 *   <li>Название</li>
 *   <li>Описание</li>
 *   <li>Статус</li>
 *   <li>Приоритет</li>
 *   <li>Срок выполнения</li>
 *   <li>Связь с пользователем</li>
 * </ul>
 * </p>
 */
@Entity
@Getter
@Setter
public class Task {
    /**
     * Уникальный идентификатор задачи.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Название задачи.
     */
    private String title;
    /**
     * Описание задачи.
     */
    private String description;
    /**
     * Текущий статус задачи (например, NEW, IN_PROGRESS, COMPLETED).
     */
    private String status;
    /**
     * Приоритет задачи (например, LOW, MEDIUM, HIGH).
     */
    private String priority;
    /**
     * Срок выполнения задачи.
     */
    private LocalDateTime dueDate;

    /**
     * Пользователь, к которому привязана задача.
     */
    @ManyToOne
    private User user;
}
