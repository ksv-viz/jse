package ru.ksv.tm.controller;

import ru.ksv.tm.entity.Project;
import ru.ksv.tm.service.ProjectService;

public class ProjectController extends AbstractController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    public int createProject() {
        System.out.println("[CREATE PROJECT]");
        System.out.println("PLEASE, ENTER PROJECT NAME:");
        final String name = scanner.nextLine();
        projectService.create(name);
        System.out.println("[OK]");
        return 0;
    }

    public int clearProject() {
        System.out.println("[CLEAR PROJECT]");
        projectService.clear();
        System.out.println("[OK]");
        return 0;
    }

    public int listProject() {
        System.out.println("[LIST PROJECT]");
        int index = 1;
        for (final Project project : projectService.findAll()) {
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
        scanner.nextLine();
        final Project project = projectService.findById(id);
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
        scanner.nextLine();
        final Project project = projectService.findByIndex(index);
        viewProject(project);
        return 0;
    }

    public int viewProjectByName() {
        System.out.println("[ENTER, PROJECT NAME]");
        final String name = scanner.nextLine();
        final Project project = projectService.findByName(name);
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
        final Project project = projectService.findById(id);
        if (project == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER PROJECT NAME:");
        final String name = scanner.nextLine();
        System.out.println("PLEASE, ENTER PROJECT DESCRIPTION:");
        final String description = scanner.nextLine();
        projectService.update(project.getId(), name, description);
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
        final Project project = projectService.findByIndex(index);
        if (project == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER PROJECT NAME:");
        final String name = scanner.nextLine();
        System.out.println("PLEASE, ENTER PROJECT DESCRIPTION:");
        final String description = scanner.nextLine();
        projectService.update(project.getId(), name, description);
        System.out.println("[OK]");
        return 0;
    }

    public int updateProjectByName() {
        System.out.println("[UPDATE PROJECT BY NAME]");
        System.out.println("PLEASE, ENTER PROJECT NAME");
        final String findingName = scanner.nextLine();
        final Project project = projectService.findByName(findingName);
        if (project == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER PROJECT NAME:");
        final String name = scanner.nextLine();
        System.out.println("PLEASE, ENTER PROJECT DESCRIPTION:");
        final String description = scanner.nextLine();
        projectService.update(project.getId(), name, description);
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
        scanner.nextLine();
        final Project project = projectService.removeById(id);
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
        scanner.nextLine();
        final Project project = projectService.removeByIndex(index);
        if (project == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
        return 0;
    }

    public int removeProjectByName() {
        System.out.println("[REMOVE PROJECT BY NAME]");
        System.out.println("PLEASE, ENTER PROJECT NAME");
        final String name = scanner.nextLine();
        final Project project = projectService.removeByName(name);
        if (project == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
        return 0;
    }

}
