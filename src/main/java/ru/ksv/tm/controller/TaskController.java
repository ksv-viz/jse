package ru.ksv.tm.controller;

import ru.ksv.tm.entity.Task;
import ru.ksv.tm.service.TaskService;

public class TaskController extends AbstractController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public int createTask() {
        System.out.println("[CREATE TASK]");
        System.out.println("PLEASE, ENTER TASK NAME:");
        final String name = scanner.nextLine();
        taskService.create(name);
        System.out.println("[OK]");
        return 0;
    }

    public int clearTask() {
        System.out.println("[CLEAR TASK]");
        taskService.clear();
        System.out.println("[OK]");
        return 0;
    }

    public int listTask() {
        System.out.println("[LIST TASK]");
        int index = 1;
        for (final Task task : taskService.findAll()) {
            System.out.println(index + ". " + task.getId() + ": " + task.getName());
            index++;
        }
        System.out.println("[OK]");
        return 0;
    }

    public void viewTask(final Task task) {
        if (task == null) return;
        System.out.println("[VIEW TASK]");
        System.out.println("ID: " + task.getId());
        System.out.println("NAME: " + task.getName());
        System.out.println("DESCRIPTION: " + task.getDescription());
        System.out.println("[OK]");
    }

    public int viewTaskById() {
        System.out.println("[ENTER, TASK ID]");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long id = scanner.nextLong();
        final Task task = taskService.findById(id);
        viewTask(task);
        return 0;
    }

    public int viewTaskByIndex() {
        System.out.println("[ENTER, TASK INDEX]");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final int index = scanner.nextInt() - 1;
        final Task task = taskService.findByIndex(index);
        viewTask(task);
        return 0;
    }

    public int viewTaskByName() {
        System.out.println("[ENTER, TASK NAME]");
        final String name = scanner.nextLine();
        final Task task = taskService.findByName(name);
        viewTask(task);
        return 0;
    }

    public int updateTaskById() {
        System.out.println("[UPDATE TASK BY ID]");
        System.out.println("PLEASE, ENTER TASK ID");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long id = scanner.nextLong();
        scanner.nextLine();
        final Task task = taskService.findById(id);
        if (task == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER TASK NAME:");
        final String name = scanner.nextLine();
        System.out.println("PLEASE, ENTER TASK DESCRIPTION:");
        final String description = scanner.nextLine();
        taskService.update(task.getId(), name, description);
        System.out.println("[OK]");
        return 0;
    }

    public int updateTaskByIndex() {
        System.out.println("[UPDATE TASK BY INDEX]");
        System.out.println("PLEASE, ENTER TASK INDEX");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final int index = scanner.nextInt() - 1;
        scanner.nextLine();
        final Task task = taskService.findByIndex(index);
        if (task == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER TASK NAME:");
        final String name = scanner.nextLine();
        System.out.println("PLEASE, ENTER TASK DESCRIPTION:");
        final String description = scanner.nextLine();
        taskService.update(task.getId(), name, description);
        System.out.println("[OK]");
        return 0;
    }

    public int updateTaskByName() {
        System.out.println("[UPDATE TASK BY NAME]");
        System.out.println("PLEASE, ENTER TASK NAME");
        final String findingName = scanner.nextLine();
        final Task task = taskService.findByName(findingName);
        if (task == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER TASK NAME:");
        final String name = scanner.nextLine();
        System.out.println("PLEASE, ENTER TASK DESCRIPTION:");
        final String description = scanner.nextLine();
        taskService.update(task.getId(), name, description);
        System.out.println("[OK]");
        return 0;
    }

    public int removeTaskById() {
        System.out.println("[REMOVE TASK BY ID]");
        System.out.println("PLEASE, ENTER TASK ID");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long id = scanner.nextLong();
        final Task task = taskService.removeById(id);
        if (task == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
        return 0;
    }

    public int removeTaskByIndex() {
        System.out.println("[REMOVE TASK BY INDEX]");
        System.out.println("PLEASE, ENTER TASK INDEX");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final int index = scanner.nextInt() - 1;
        final Task task = taskService.removeByIndex(index);
        if (task == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
        return 0;
    }

    public int removeTaskByName() {
        System.out.println("[REMOVE TASK BY NAME]");
        System.out.println("PLEASE, ENTER TASK NAME");
        final String name = scanner.nextLine();
        final Task task = taskService.removeByName(name);
        if (task == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
        return 0;
    }

}