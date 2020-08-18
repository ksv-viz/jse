package ru.ksv.tm.controller;

import ru.ksv.tm.entity.Task;
import ru.ksv.tm.service.TaskService;
import ru.ksv.tm.service.ProjectTaskService;
import ru.ksv.tm.service.UserProjectService;
import ru.ksv.tm.service.UserTaskService;

import java.util.List;

public class TaskController extends AbstractController {

    private final TaskService taskService;

    private final ProjectTaskService projectTaskService;

    private final UserTaskService userTaskService;

    public TaskController(TaskService taskService, ProjectTaskService projectTaskService, UserTaskService userTaskService) {
        this.taskService = taskService;
        this.projectTaskService = projectTaskService;
        this.userTaskService = userTaskService;
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
        scanner.nextLine();
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
        scanner.nextLine();
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

    public void viewTasks(final List<Task> tasks){
        if (tasks == null || tasks.isEmpty()) return;
        int index = 1;
        for (final Task task : tasks) {
            System.out.println(index + ". " + task.getId() + ": " + task.getName() + " [DESC: " + task.getDescription() + "]");
            index++;
        }
    }

    public int listTaskByProjectId(){
        System.out.println("[LIST TASK BY PROJECT]");
        System.out.println("PLEASE, ENTER PROJECT ID:");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long projectId = scanner.nextLong();
        scanner.nextLine();
        final List<Task> tasks = taskService.findAllByProjectId(projectId);
        viewTasks(tasks);
        System.out.println("[OK]");
        return 0;
    }

    public int addTaskToProjectByIds(){
        System.out.println("[ADD TASK TO PROJECT BY IDS]");
        System.out.println("PLEASE, ENTER PROJECT ID:");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long projectId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("PLEASE, ENTER TASK ID:");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long taskId = scanner.nextLong();
        scanner.nextLine();
        projectTaskService.addTaskToProject(projectId, taskId);
        System.out.println("[OK]");
        return 0;
    }

    public int removeTaskFromProjectByIds(){
        System.out.println("[REMOVE TASK FROM PROJECT BY IDS]");
        System.out.println("Please, enter project id:");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long projectId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Please, enter task id:");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long taskId = scanner.nextLong();
        scanner.nextLine();
        projectTaskService.removeTaskToProject(projectId, taskId);
        System.out.println("[OK]");
        return 0;
    }
    public int listTaskByUserId(){
        System.out.println("[LIST TASK BY USER]");
        System.out.println("PLEASE, ENTER USER ID:");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long userId = scanner.nextLong();
        scanner.nextLine();
        final List<Task> tasks = taskService.findAllByUserId(userId);
        viewTasks(tasks);
        System.out.println("[OK]");
        return 0;
    }

    public int addTaskToUserByIds(){
        System.out.println("[ADD TASK TO USER BY IDS]");
        System.out.println("PLEASE, ENTER USER ID:");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long userId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("PLEASE, ENTER TASK ID:");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long taskId = scanner.nextLong();
        scanner.nextLine();
        userTaskService.addTaskToUser(userId, taskId);
        System.out.println("[OK]");
        return 0;
    }

    public int removeTaskFromUserByIds(){
        System.out.println("[REMOVE TASK FROM USER BY IDS]");
        System.out.println("Please, enter user id:");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long userId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Please, enter task id:");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long taskId = scanner.nextLong();
        scanner.nextLine();
        userTaskService.removeTaskToUser(userId, taskId);
        System.out.println("[OK]");
        return 0;
    }

}
