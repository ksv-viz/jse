package ru.ksv.tm.service;

import ru.ksv.tm.entity.User;
import ru.ksv.tm.entity.Project;
import ru.ksv.tm.repository.UserRepository;
import ru.ksv.tm.repository.ProjectRepository;

import java.util.Collections;
import java.util.List;

public class UserProjectService {
    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;

    public UserProjectService(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public List<Project> findAllByUserId(final Long userId) {
        if (userId == null) return Collections.emptyList();
        return projectRepository.findAllByUserId(userId);
    }

    public Project removeProjectToUser(final Long userId, final Long projectId) {
        final Project project = projectRepository.findByUserIdAndId(userId, projectId);
        if (project == null) return null;
        project.setUserId(null);
        return project;
    }

    public Project addProjectToUser(final Long userId, final Long projectId) {
        final User user = userRepository.findById(userId);
        if (user == null) return null;
        final Project project = projectRepository.findById(projectId);
        if (project == null) return null;
        project.setUserId(userId);
        return project;
    }

}
