package ru.ksv.tm.service;

import ru.ksv.tm.entity.User;
import ru.ksv.tm.entity.Task;
import ru.ksv.tm.repository.UserRepository;
import ru.ksv.tm.repository.TaskRepository;

import java.util.Collections;
import java.util.List;

public class UserTaskService {
    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    public UserTaskService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllByUserId(final Long userId) {
        if (userId == null) return Collections.emptyList();
        return taskRepository.findAllByUserId(userId);
    }

    public Task removeTaskToUser(final Long userId, final Long taskId) {
        final Task task = taskRepository.findByUserIdAndId(userId, taskId);
        if (task == null) return null;
        task.setUserId(null);
        return task;
    }

    public Task addTaskToUser(final Long userId, final Long taskId) {
        final User user = userRepository.findById(userId);
        if (user == null) return null;
        final Task task = taskRepository.findById(taskId);
        if (task == null) return null;
        task.setUserId(userId);
        return task;
    }

}
