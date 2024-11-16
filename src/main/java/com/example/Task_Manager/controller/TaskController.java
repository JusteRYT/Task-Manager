package com.example.Task_Manager.controller;

import com.example.Task_Manager.model.Task;
import com.example.Task_Manager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для управления задачами (`Task`).
 *
 * <p>Обрабатывает HTTP-запросы для выполнения CRUD-операций над задачами.</p>
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    /**
     * Конструктор для внедрения зависимостей.
     *
     * @param taskService сервис для работы с задачами
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Создает новую задачу.
     *
     * @param task объект задачи
     * @return созданная задача
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    /**
     * Получает задачу по идентификатору.
     *
     * @param id идентификатор задачи
     * @return задача, если найдена
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Получает список задач для указанного пользователя.
     *
     * @param userId идентификатор пользователя
     * @return список задач
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTaskByUserId(userId);
        return ResponseEntity.ok(tasks);
    }

    /**
     * Обновляет задачу.
     *
     * @param task объект задачи с обновленными данными
     * @return обновленная задача
     */
    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        Task updatedTask = taskService.updateTask(task);
        return ResponseEntity.ok(updatedTask);
    }

    /**
     * Удаляет задачу по идентификатору.
     *
     * @param id идентификатор задачи
     * @return статус операции
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Task deletedTusk = taskService.getTaskById(id).orElse(null);
        taskService.deleteTask(deletedTusk);
        return ResponseEntity.noContent().build();
    }
}
