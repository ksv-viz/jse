package ru.ksv.tm.service;

import ru.ksv.tm.entity.Task;
import ru.ksv.tm.repository.TaskRepository;

import java.util.List;

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(String name) {
        if (name == null || name.isEmpty()) return null;
        return taskRepository.create(name);
    }

    public Task create(String name, String description) {
        if (name == null || name.isEmpty()) return null;
        if (description == null || description.isEmpty()) return null;
        return taskRepository.create(name, description);
    }

    public Task update(Long id, String name, String description) {
        if (id == null) return null;
        if (name == null || name.isEmpty()) return null;
        if (description == null || description.isEmpty()) return null;
        return taskRepository.update(id, name, description);
    }

    public void clear() {
        taskRepository.clear();
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id);
    }

    public Task findByName(String name) {
        return taskRepository.findByName(name);
    }

    public Task findByIndex(int index) {
        return taskRepository.findByIndex(index);
    }

    public Task removeById(Long id) {
        return taskRepository.removeById(id);
    }

    public Task removeByIndex(int index) {
        return taskRepository.removeByIndex(index);
    }

    public Task removeByName(String name) {
        return taskRepository.removeByName(name);
    }
}
