package com.example.Task_Manager.service;

import com.example.Task_Manager.model.Task;
import com.example.Task_Manager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с задачами (`Task`).
 *
 * <p>Обеспечивает бизнес-логику для операций над задачами,
 * включая создание, получение, обновление и удаление.</p>
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     * Конструктор для внедрения зависимостей.
     *
     * @param taskRepository репозиторий для работы с задачами
     */
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Создает новую задачу.
     *
     * @param task объект задачи
     * @return сохраненная задача
     */
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Находит задачу по идентификатору.
     *
     * @param id идентификатор задачи
     * @return задача, если найдена
     */
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    /**
     * Получает список всех задач для пользователя.
     *
     * @param userId идентификатор пользователя
     * @return список задач
     */
    public List<Task> getTaskByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    /**
     * Обновляет данные задачи.
     *
     * @param task объект задачи с обновленными данными
     * @return обновленная задача
     */
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Удаляет задачу по идентификатору.
     *
     * @param task идентификатор задачи
     */
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
}
