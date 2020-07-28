package ru.ksv.tm;

import ru.ksv.tm.dao.ProjectDAO;
import ru.ksv.tm.dao.TaskDAO;
import ru.ksv.tm.entity.Project;
import ru.ksv.tm.entity.Task;

import java.time.chrono.MinguoDate;
import java.util.Scanner;

import static ru.ksv.tm.constant.TerminalConst.*;

public class Application {

    private static final ProjectDAO projectDAO = new ProjectDAO();

    private static final TaskDAO taskDAO = new TaskDAO();

    private static final Scanner scanner = new Scanner(System.in);

    static {
        projectDAO.create("PROJECT 1");
        projectDAO.create("PROJECT 2");
        projectDAO.create("PROJECT 3");
        projectDAO.create("PROJECT 4");
        taskDAO.create("TASK 1");
        taskDAO.create("TASK 2");
        taskDAO.create("TASK 3");
        taskDAO.create("TASK 4");
    }

    public static void main(final String[] args) {
        run(args);
        displayWelcome();
        process();
    }

    private static void run(final String[] args) {
        if (args == null) return;
        if (args.length < 1) return;
        final String param = args[0];
        final int result = run(param);
        System.exit(result);
    }

    private static int run(final String param) {
        if (param == null || param.isEmpty()) return -1;
        switch (param) {
            case VERSION:
                return displayVersion();
            case ABOUT:
                return displayAbout();
            case HELP:
                return displayHelp();
            case EXIT:
                return exit();

            case PROJECT_CREATE:
                return createProject();
            case PROJECT_CLEAR:
                return clearProject();
            case PROJECT_LIST:
                return listProject();
            case PROJECT_VIEW_BY_ID:
                return viewProjectById();
            case PROJECT_VIEW_BY_INDEX:
                return viewProjectByIndex();
            case PROJECT_VIEW_BY_NAME:
                return viewProjectByName();
            case PROJECT_UPDATE_BY_ID:
                return updateProjectById();
            case PROJECT_UPDATE_BY_INDEX:
                return updateProjectByIndex();
            case PROJECT_UPDATE_BY_NAME:
                return updateProjectByName();
            case PROJECT_REMOVE_BY_ID:
                return removeProjectById();
            case PROJECT_REMOVE_BY_INDEX:
                return removeProjectByIndex();
            case PROJECT_REMOVE_BY_NAME:
                return removeProjectByName();

            case TASK_CREATE:
                return createTask();
            case TASK_CLEAR:
                return clearTask();
            case TASK_LIST:
                return listTask();
            case TASK_VIEW_BY_ID:
                return viewTaskById();
            case TASK_VIEW_BY_INDEX:
                return viewTaskByIndex();
            case TASK_VIEW_BY_NAME:
                return viewTaskByName();
            case TASK_UPDATE_BY_ID:
                return updateTaskById();
            case TASK_UPDATE_BY_INDEX:
                return updateTaskByIndex();
            case TASK_UPDATE_BY_NAME:
                return updateTaskByName();
            case TASK_REMOVE_BY_ID:
                return removeTaskById();
            case TASK_REMOVE_BY_INDEX:
                return removeTaskByIndex();
            case TASK_REMOVE_BY_NAME:
                return removeTaskByName();

            default:
                return displayError(param);
        }
    }

    private static int createProject() {
        System.out.println("[CREATE PROJECT]");
        System.out.println("PLEASE, ENTER PROJECT NAME:");
        final String name = scanner.nextLine();
        projectDAO.create(name);
        System.out.println("[OK]");
        return 0;
    }

    private static int clearProject() {
        System.out.println("[CLEAR PROJECT]");
        projectDAO.clear();
        System.out.println("[OK]");
        return 0;
    }

    private static int listProject() {
        System.out.println("[LIST PROJECT]");
        int index = 1;
        for (final Project project : projectDAO.findAll()) {
            System.out.println(index + ". " + project.getId() + ": " + project.getName());
            index++;
        }
        System.out.println("[OK]");
        return 0;
    }

    private static void viewProject(final Project project) {
        if (project == null) return;
        System.out.println("[VIEW PROJECT]");
        System.out.println("ID: " + project.getId());
        System.out.println("NAME: " + project.getName());
        System.out.println("DESCRIPTION: " + project.getDescription());
        System.out.println("[OK]");
    }

    private static int viewProjectById() {
        System.out.println("[ENTER, PROJECT ID]");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long id = scanner.nextLong();
        final Project project = projectDAO.findById(id);
        viewProject(project);
        return 0;
    }

    private static int viewProjectByIndex() {
        System.out.println("[ENTER, PROJECT INDEX]");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final int index = scanner.nextInt() - 1;
        final Project project = projectDAO.findByIndex(index);
        viewProject(project);
        return 0;
    }

    private static int viewProjectByName() {
        System.out.println("[ENTER, PROJECT NAME]");
        final String name = scanner.nextLine();
        final Project project = projectDAO.findByName(name);
        viewProject(project);
        return 0;
    }

    private static int updateProjectById() {
        System.out.println("[UPDATE PROJECT BY ID]");
        System.out.println("PLEASE, ENTER PROJECT ID");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long id = scanner.nextLong();
        scanner.nextLine();
        final Project project = projectDAO.findById(id);
        if (project == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER PROJECT NAME:");
        final String name = scanner.nextLine();
        System.out.println("PLEASE, ENTER PROJECT DESCRIPTION:");
        final String description = scanner.nextLine();
        projectDAO.update(project.getId(), name, description);
        System.out.println("[OK]");
        return 0;
    }

    private static int updateProjectByIndex() {
        System.out.println("[UPDATE PROJECT BY INDEX]");
        System.out.println("PLEASE, ENTER PROJECT INDEX");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final int index = scanner.nextInt() - 1;
        scanner.nextLine();
        final Project project = projectDAO.findByIndex(index);
        if (project == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER PROJECT NAME:");
        final String name = scanner.nextLine();
        System.out.println("PLEASE, ENTER PROJECT DESCRIPTION:");
        final String description = scanner.nextLine();
        projectDAO.update(project.getId(), name, description);
        System.out.println("[OK]");
        return 0;
    }

    private static int updateProjectByName() {
        System.out.println("[UPDATE PROJECT BY NAME]");
        System.out.println("PLEASE, ENTER PROJECT NAME");
        final String findingName = scanner.nextLine();
        final Project project = projectDAO.findByName(findingName);
        if (project == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER TASK NAME:");
        final String name = scanner.nextLine();
        System.out.println("PLEASE, ENTER TASK DESCRIPTION:");
        final String description = scanner.nextLine();
        projectDAO.update(project.getId(), name, description);
        System.out.println("[OK]");
        return 0;
    }

    private static int removeProjectById() {
        System.out.println("[REMOVE PROJECT BY ID]");
        System.out.println("PLEASE, ENTER PROJECT ID");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long id = scanner.nextLong();
        final Project project = projectDAO.removeById(id);
        if (project == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
        return 0;
    }

    private static int removeProjectByIndex() {
        System.out.println("[REMOVE PROJECT BY INDEX]");
        System.out.println("PLEASE, ENTER PROJECT INDEX");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final int index = scanner.nextInt() - 1;
        final Project project = projectDAO.removeByIndex(index);
        if (project == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
        return 0;
    }

    private static int removeProjectByName() {
        System.out.println("[REMOVE PROJECT BY NAME]");
        System.out.println("PLEASE, ENTER PROJECT NAME");
        final String name = scanner.nextLine();
        final Project project = projectDAO.removeByName(name);
        if (project == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
        return 0;
    }

    private static int createTask() {
        System.out.println("[CREATE TASK]");
        System.out.println("PLEASE, ENTER TASK NAME:");
        final String name = scanner.nextLine();
        taskDAO.create(name);
        System.out.println("[OK]");
        return 0;
    }

    private static int clearTask() {
        System.out.println("[CLEAR TASK]");
        taskDAO.clear();
        System.out.println("[OK]");
        return 0;
    }

    private static int listTask() {
        System.out.println("[LIST TASK]");
        int index = 1;
        for (final Task task : taskDAO.findAll()) {
            System.out.println(index + ". " + task.getId() + ": " + task.getName());
            index++;
        }
        System.out.println("[OK]");
        return 0;
    }

    private static void viewTask(final Task task) {
        if (task == null) return;
        System.out.println("[VIEW TASK]");
        System.out.println("ID: " + task.getId());
        System.out.println("NAME: " + task.getName());
        System.out.println("DESCRIPTION: " + task.getDescription());
        System.out.println("[OK]");
    }

    private static int viewTaskById() {
        System.out.println("[ENTER, TASK ID]");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long id = scanner.nextLong();
        final Task task = taskDAO.findById(id);
        viewTask(task);
        return 0;
    }

    private static int viewTaskByIndex() {
        System.out.println("[ENTER, TASK INDEX]");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final int index = scanner.nextInt() - 1;
        final Task task = taskDAO.findByIndex(index);
        viewTask(task);
        return 0;
    }

    private static int viewTaskByName() {
        System.out.println("[ENTER, TASK NAME]");
        final String name = scanner.nextLine();
        final Task task = taskDAO.findByName(name);
        viewTask(task);
        return 0;
    }

    private static int updateTaskById() {
        System.out.println("[UPDATE TASK BY ID]");
        System.out.println("PLEASE, ENTER TASK ID");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long id = scanner.nextLong();
        scanner.nextLine();
        final Task task = taskDAO.findById(id);
        if (task == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER TASK NAME:");
        final String name = scanner.nextLine();
        System.out.println("PLEASE, ENTER TASK DESCRIPTION:");
        final String description = scanner.nextLine();
        taskDAO.update(task.getId(), name, description);
        System.out.println("[OK]");
        return 0;
    }

    private static int updateTaskByIndex() {
        System.out.println("[UPDATE TASK BY INDEX]");
        System.out.println("PLEASE, ENTER TASK INDEX");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final int index = scanner.nextInt() - 1;
        scanner.nextLine();
        final Task task = taskDAO.findByIndex(index);
        if (task == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER TASK NAME:");
        final String name = scanner.nextLine();
        System.out.println("PLEASE, ENTER TASK DESCRIPTION:");
        final String description = scanner.nextLine();
        taskDAO.update(task.getId(), name, description);
        System.out.println("[OK]");
        return 0;
    }

    private static int updateTaskByName() {
        System.out.println("[UPDATE TASK BY NAME]");
        System.out.println("PLEASE, ENTER TASK NAME");
        final String findingName = scanner.nextLine();
        final Task task = taskDAO.findByName(findingName);
        if (task == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER TASK NAME:");
        final String name = scanner.nextLine();
        System.out.println("PLEASE, ENTER TASK DESCRIPTION:");
        final String description = scanner.nextLine();
        taskDAO.update(task.getId(), name, description);
        System.out.println("[OK]");
        return 0;
    }

    private static int removeTaskById() {
        System.out.println("[REMOVE TASK BY ID]");
        System.out.println("PLEASE, ENTER TASK ID");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long id = scanner.nextLong();
        final Task task = taskDAO.removeById(id);
        if (task == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
        return 0;
    }

    private static int removeTaskByIndex() {
        System.out.println("[REMOVE TASK BY INDEX]");
        System.out.println("PLEASE, ENTER TASK INDEX");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final int index = scanner.nextInt() - 1;
        final Task task = taskDAO.removeByIndex(index);
        if (task == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
        return 0;
    }

    private static int removeTaskByName() {
        System.out.println("[REMOVE TASK BY NAME]");
        System.out.println("PLEASE, ENTER TASK NAME");
        final String name = scanner.nextLine();
        final Task task = taskDAO.removeByName(name);
        if (task == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
        return 0;
    }

    private static void process() {
        String command = "";
        while (!EXIT.equals(command)) {
            command = scanner.nextLine();
            run(command);
            System.out.println();
        }
    }

    private static int exit() {
        System.exit(0);
        return 0;
    }

    private static int displayVersion() {
        System.out.println("1.0.0.1");
        return 0;
    }

    private static int displayAbout() {
        System.out.println("Serg V. Kazakov");
        System.out.println("serg.v.kazakov@mail.ru");
        return 0;
    }


    private static int displayHelp() {
        System.out.println("version - Display application version.");
        System.out.println("about - Display developer info.");
        System.out.println("help - Display list of commands.");
        System.out.println("exit - Terminate console application.");
        System.out.println();
        System.out.println("project-create - Create new project by name.");
        System.out.println("project-list - Display list of projects.");
        System.out.println("project-clear - Remove all projects.");
        System.out.println("project-view-by-id - View project by id.");
        System.out.println("project-view-by-index - View project by index.");
        System.out.println("project-view-by-name - View project by name.");
        System.out.println("project-remove-by-id - Remove project by id.");
        System.out.println("project-remove-by-index - Remove project by index.");
        System.out.println("project-remove-by-name - Remove project by name.");
        System.out.println("project-update-by-id - Update project by id.");
        System.out.println("project-update-by-index - Update project by index.");
        System.out.println("project-update-by-name - Update project by name.");
        System.out.println();
        System.out.println("task-create - Create new task by name.");
        System.out.println("task-list - Display list of tasks.");
        System.out.println("task-clear - Remove all tasks.");
        System.out.println("task-view-by-id - View task by id.");
        System.out.println("task-view-by-index - View task by index.");
        System.out.println("task-view-by-name - View task by name.");
        System.out.println("task-remove-by-id - Remove task by id.");
        System.out.println("task-remove-by-index - Remove task by index.");
        System.out.println("task-remove-by-name - Remove task by name.");
        System.out.println("task-update-by-id - Update task by id.");
        System.out.println("task-update-by-index - Update task by index.");
        System.out.println("task-update-by-name - Update task by name.");
        return 0;
    }

    private static int displayError(final String errorparam) {
        System.out.println("Error! Unknown [" + errorparam + "] program argument...");
        System.out.println("Try one of this:");
        displayHelp();
        return -1;
    }

    private static void displayWelcome() {
        System.out.println("** WELCOME TO TASK MANAGER **");
    }

}
