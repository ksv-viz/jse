package ru.ksv.tm.service;

import ru.ksv.tm.entity.Project;
import ru.ksv.tm.repository.ProjectRepository;

import java.util.List;

public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project create(String name) {
        if (name == null || name.isEmpty()) return null;
        return projectRepository.create(name);
    }

    public Project create(String name, String description) {
        if (name == null || name.isEmpty()) return null;
        if (description == null || description.isEmpty()) return null;
        return projectRepository.create(name, description);
    }

    public Project update(Long id, String name, String description) {
        if (id == null) return null;
        if (name == null || name.isEmpty()) return null;
        if (description == null || description.isEmpty()) return null;
        return projectRepository.update(id, name, description);
    }

    public void clear() {
        projectRepository.clear();
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(Long id) {
        return projectRepository.findById(id);
    }

    public Project findByName(String name) {
        return projectRepository.findByName(name);
    }

    public Project findByIndex(int index) {
        return projectRepository.findByIndex(index);
    }

    public Project removeById(Long id) {
        return projectRepository.removeById(id);
    }

    public Project removeByIndex(int index) {
        return projectRepository.removeByIndex(index);
    }

    public Project removeByName(String name) {
        return projectRepository.removeByName(name);
    }
}
