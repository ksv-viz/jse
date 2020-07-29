package ru.ksv.tm.controller;

import ru.ksv.tm.repository.ProjectRepository;
import ru.ksv.tm.entity.Project;

public class ProjectController extends AbstractController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public int createProject() {
        System.out.println("[CREATE PROJECT]");
        System.out.println("PLEASE, ENTER PROJECT NAME:");
        final String name = scanner.nextLine();
        projectRepository.create(name);
        System.out.println("[OK]");
        return 0;
    }

    public int clearProject() {
        System.out.println("[CLEAR PROJECT]");
        projectRepository.clear();
        System.out.println("[OK]");
        return 0;
    }

    public int listProject() {
        System.out.println("[LIST PROJECT]");
        int index = 1;
        for (final Project project : projectRepository.findAll()) {
            System.out.println(index + ". " + project.getId() + ": " + project.getName());
            index++;
        }
        System.out.println("[OK]");
        return 0;
    }

    public void viewProject(final Project project) {
        if (project == null) return;
        System.out.println("[VIEW PROJECT]");
        System.out.println("ID: " + project.getId());
        System.out.println("NAME: " + project.getName());
        System.out.println("DESCRIPTION: " + project.getDescription());
        System.out.println("[OK]");
    }

    public int viewProjectById() {
        System.out.println("[ENTER, PROJECT ID]");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long id = scanner.nextLong();
        final Project project = projectRepository.findById(id);
        viewProject(project);
        return 0;
    }

    public int viewProjectByIndex() {
        System.out.println("[ENTER, PROJECT INDEX]");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final int index = scanner.nextInt() - 1;
        final Project project = projectRepository.findByIndex(index);
        viewProject(project);
        return 0;
    }

    public int viewProjectByName() {
        System.out.println("[ENTER, PROJECT NAME]");
        final String name = scanner.nextLine();
        final Project project = projectRepository.findByName(name);
        viewProject(project);
        return 0;
    }

    public int updateProjectById() {
        System.out.println("[UPDATE PROJECT BY ID]");
        System.out.println("PLEASE, ENTER PROJECT ID");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long id = scanner.nextLong();
        scanner.nextLine();
        final Project project = projectRepository.findById(id);
        if (project == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER PROJECT NAME:");
        final String name = scanner.nextLine();
        System.out.println("PLEASE, ENTER PROJECT DESCRIPTION:");
        final String description = scanner.nextLine();
        projectRepository.update(project.getId(), name, description);
        System.out.println("[OK]");
        return 0;
    }

    public int updateProjectByIndex() {
        System.out.println("[UPDATE PROJECT BY INDEX]");
        System.out.println("PLEASE, ENTER PROJECT INDEX");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final int index = scanner.nextInt() - 1;
        scanner.nextLine();
        final Project project = projectRepository.findByIndex(index);
        if (project == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER PROJECT NAME:");
        final String name = scanner.nextLine();
        System.out.println("PLEASE, ENTER PROJECT DESCRIPTION:");
        final String description = scanner.nextLine();
        projectRepository.update(project.getId(), name, description);
        System.out.println("[OK]");
        return 0;
    }

    public int updateProjectByName() {
        System.out.println("[UPDATE PROJECT BY NAME]");
        System.out.println("PLEASE, ENTER PROJECT NAME");
        final String findingName = scanner.nextLine();
        final Project project = projectRepository.findByName(findingName);
        if (project == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER TASK NAME:");
        final String name = scanner.nextLine();
        System.out.println("PLEASE, ENTER TASK DESCRIPTION:");
        final String description = scanner.nextLine();
        projectRepository.update(project.getId(), name, description);
        System.out.println("[OK]");
        return 0;
    }

    public int removeProjectById() {
        System.out.println("[REMOVE PROJECT BY ID]");
        System.out.println("PLEASE, ENTER PROJECT ID");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long id = scanner.nextLong();
        final Project project = projectRepository.removeById(id);
        if (project == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
        return 0;
    }

    public int removeProjectByIndex() {
        System.out.println("[REMOVE PROJECT BY INDEX]");
        System.out.println("PLEASE, ENTER PROJECT INDEX");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final int index = scanner.nextInt() - 1;
        final Project project = projectRepository.removeByIndex(index);
        if (project == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
        return 0;
    }

    public int removeProjectByName() {
        System.out.println("[REMOVE PROJECT BY NAME]");
        System.out.println("PLEASE, ENTER PROJECT NAME");
        final String name = scanner.nextLine();
        final Project project = projectRepository.removeByName(name);
        if (project == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
        return 0;
    }

}
