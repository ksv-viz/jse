package ru.ksv.tm.repository;

import ru.ksv.tm.entity.Project;
import ru.ksv.tm.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {

    private final List<Project> projects = new ArrayList<>();

    public Project create(final String name) {
        final Project project = new Project(name);
        projects.add(project);
        return project;
    }

    public Project create(final String name, final String description) {
        final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        projects.add(project);
        return project;
    }

    public Project update(final Long id, final String name, final String description) {
        final Project project = findById(id);
        if (project == null) return null;
        project.setId(id);
        project.setName(name);
        project.setDescription(description);
        return project;
    }

    public void clear() {
        projects.clear();
    }

    public List<Project> findAll() {
        return projects;
    }

    public Project findById(final Long id) {
        for (Project project : projects) {
            if (project.getId().equals(id)) return project;
        }
        return null;
    }

    public Project findByName(final String name) {
        for (Project project : projects) {
            if (project.getName().equals(name)) return project;
        }
        return null;
    }

    public Project findByIndex(final int index) {
        return projects.get(index);
    }

    public Project removeById(final Long id) {
        final Project project = findById(id);
        if (project == null) return null;
        projects.remove(project);
        return project;
    }

    public Project removeByIndex(final int index) {
        final Project project = findByIndex(index);
        if (project == null) return null;
        projects.remove(project);
        return project;
    }

    public Project removeByName(final String name) {
        final Project project = findByName(name);
        if (project == null) return null;
        projects.remove(project);
        return project;
    }

    public List<Project> findAllByUserId(final Long userId){
        final List<Project> result = new ArrayList<>();
        for (final Project project: findAll()){
            final Long idUser = project.getUserId();
            if (idUser == null) continue;
            if (idUser.equals(userId)) result.add(project);
        }
        return result;
    }

    public Project findByUserIdAndId(final Long userId, final Long id) {
        if (id == null) return null;
        for (final Project project: projects) {
            final Long idUser = project.getUserId();
            if (idUser == null) continue;
            if (!idUser.equals(userId)) continue;
            if (project.getId().equals(id)) return project;
        }
        return null;
    }

}
