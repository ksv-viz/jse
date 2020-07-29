package ru.ksv.tm;

import ru.ksv.tm.controller.SystemController;
import ru.ksv.tm.controller.ProjectController;
import ru.ksv.tm.controller.TaskController;
import ru.ksv.tm.repository.ProjectRepository;
import ru.ksv.tm.repository.TaskRepository;

import java.util.Scanner;

import static ru.ksv.tm.constant.TerminalConst.*;

public class Application {

    private final ProjectRepository projectRepository = new ProjectRepository();

    private final TaskRepository taskRepository = new TaskRepository();

    private final ProjectController projectController = new ProjectController(projectRepository);

    private final TaskController taskController = new TaskController(taskRepository);

    private final SystemController systemController = new SystemController();

    {
        projectRepository.create("PROJECT 1");
        projectRepository.create("PROJECT 2");
        projectRepository.create("PROJECT 3");
        projectRepository.create("PROJECT 4");
        taskRepository.create("TASK 1");
        taskRepository.create("TASK 2");
        taskRepository.create("TASK 3");
        taskRepository.create("TASK 4");
    }

    public static void main(final String[] args) {
        final Application application = new Application();
        application.run(args);
        application.systemController.displayWelcome();
        application.process(application);
    }

    private void process(final Application application) {
        final Scanner scanner = new Scanner(System.in);
        String command = "";
        while (!EXIT.equals(command)) {
            command = scanner.nextLine();
            application.run(command);
            System.out.println();
        }
    }

    public void run(final String[] args) {
        if (args == null) return;
        if (args.length < 1) return;
        final String param = args[0];
        final int result = run(param);
        System.exit(result);
    }

    public int run(final String param) {
        if (param == null || param.isEmpty()) return -1;
        switch (param) {
            case VERSION:
                return systemController.displayVersion();
            case ABOUT:
                return systemController.displayAbout();
            case HELP:
                return systemController.displayHelp();
            case EXIT:
                return systemController.displayExit();

            case PROJECT_CREATE:
                return projectController.createProject();
            case PROJECT_CLEAR:
                return projectController.clearProject();
            case PROJECT_LIST:
                return projectController.listProject();
            case PROJECT_VIEW_BY_ID:
                return projectController.viewProjectById();
            case PROJECT_VIEW_BY_INDEX:
                return projectController.viewProjectByIndex();
            case PROJECT_VIEW_BY_NAME:
                return projectController.viewProjectByName();
            case PROJECT_UPDATE_BY_ID:
                return projectController.updateProjectById();
            case PROJECT_UPDATE_BY_INDEX:
                return projectController.updateProjectByIndex();
            case PROJECT_UPDATE_BY_NAME:
                return projectController.updateProjectByName();
            case PROJECT_REMOVE_BY_ID:
                return projectController.removeProjectById();
            case PROJECT_REMOVE_BY_INDEX:
                return projectController.removeProjectByIndex();
            case PROJECT_REMOVE_BY_NAME:
                return projectController.removeProjectByName();

            case TASK_CREATE:
                return taskController.createTask();
            case TASK_CLEAR:
                return taskController.clearTask();
            case TASK_LIST:
                return taskController.listTask();
            case TASK_VIEW_BY_ID:
                return taskController.viewTaskById();
            case TASK_VIEW_BY_INDEX:
                return taskController.viewTaskByIndex();
            case TASK_VIEW_BY_NAME:
                return taskController.viewTaskByName();
            case TASK_UPDATE_BY_ID:
                return taskController.updateTaskById();
            case TASK_UPDATE_BY_INDEX:
                return taskController.updateTaskByIndex();
            case TASK_UPDATE_BY_NAME:
                return taskController.updateTaskByName();
            case TASK_REMOVE_BY_ID:
                return taskController.removeTaskById();
            case TASK_REMOVE_BY_INDEX:
                return taskController.removeTaskByIndex();
            case TASK_REMOVE_BY_NAME:
                return taskController.removeTaskByName();

            default:
                return systemController.displayError(param);
        }
    }

}
