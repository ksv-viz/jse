package ru.ksv.tm;

import ru.ksv.tm.controller.SystemController;
import ru.ksv.tm.controller.ProjectController;
import ru.ksv.tm.controller.TaskController;
import ru.ksv.tm.controller.UserController;
import ru.ksv.tm.enumerated.Role;
import ru.ksv.tm.repository.ProjectRepository;
import ru.ksv.tm.repository.TaskRepository;
import ru.ksv.tm.repository.UserRepository;
import ru.ksv.tm.service.*;

import java.util.Scanner;

import static ru.ksv.tm.constant.TerminalConst.*;

public class Application {

    private final ProjectRepository projectRepository = new ProjectRepository();

    private final TaskRepository taskRepository = new TaskRepository();

    private final UserRepository userRepository = new UserRepository();

    private final ProjectService projectService = new ProjectService(projectRepository);

    private final TaskService taskService = new TaskService(taskRepository);

    private final ProjectTaskService projectTaskService = new ProjectTaskService(projectRepository, taskRepository);

    private final UserService userService = new UserService(userRepository);

    private final UserProjectService userProjectService = new UserProjectService(userRepository, projectRepository);

    private final UserTaskService userTaskService = new UserTaskService(userRepository, taskRepository);

    private final ProjectController projectController = new ProjectController(projectService, userProjectService);

    private final TaskController taskController = new TaskController(taskService, projectTaskService, userTaskService);

    private final SystemController systemController = new SystemController();

    private final UserController userController = new UserController(userService);

    private Long sessionId;


    {
        projectService.create("PROJECT 1", "DESCRIPTION 1");
        projectService.create("PROJECT 2", "DESCRIPTION 2");
        projectService.create("PROJECT 3", "DESCRIPTION 3");
        projectService.create("PROJECT 4", "DESCRIPTION 4");
        projectService.create("PROJECT 5", "DESCRIPTION 5");

        taskService.create("TASK 1", "DESCRIPTION 1");
        taskService.create("TASK 2", "DESCRIPTION 2");
        taskService.create("TASK 3", "DESCRIPTION 3");
        taskService.create("TASK 4", "DESCRIPTION 4");
        taskService.create("TASK 5", "DESCRIPTION 5");

        projectTaskService.addTaskToProject(projectService.findByIndex(0).getId(), taskService.findByIndex(0).getId());
        projectTaskService.addTaskToProject(projectService.findByIndex(0).getId(), taskService.findByIndex(4).getId());
        projectTaskService.addTaskToProject(projectService.findByIndex(2).getId(), taskService.findByIndex(2).getId());
        projectTaskService.addTaskToProject(projectService.findByIndex(4).getId(), taskService.findByIndex(3).getId());
        projectTaskService.addTaskToProject(projectService.findByIndex(4).getId(), taskService.findByIndex(1).getId());

        userService.create("admin","Иванов", "Иван", "Иванович", "Комбайнёр");
        userService.create("quest","Петров", "Пётр", "Петрович", "Прохожий");
        userService.create("vasechkin_vv","Васечкин", "Василий", "Васильевич", "Кузнец");
        userService.create("sidorov_ss","Сидоров", "Сидор", "Сидорович", "Пастух");
        userService.updateRoleByLoginName("admin", Role.ADMIN);
        userService.updateRoleByLoginName("vasechkin_vv", Role.USER);
        userService.updateRoleByLoginName("sidorov_ss", Role.USER);
        userService.updatePasswordByLoginName("admin", "admin");
        userService.updatePasswordByLoginName("quest", "quest");
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
        sessionId = userController.logonUser();
        while (!EXIT.equals(command)) {
            if (sessionId != null) System.out.print(userService.findById(sessionId).getLoginName());
            System.out.print(">");
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
            case VERSION: return systemController.displayVersion();
            case ABOUT: return systemController.displayAbout();
            case HELP: return systemController.displayHelp();
            case EXIT: return systemController.displayExit();

            case PROJECT_CREATE: return projectController.createProject();
            case PROJECT_CLEAR: return projectController.clearProject();
            case PROJECT_LIST: return projectController.listProject();
            case PROJECT_VIEW_BY_ID: return projectController.viewProjectById();
            case PROJECT_VIEW_BY_INDEX: return projectController.viewProjectByIndex();
            case PROJECT_VIEW_BY_NAME: return projectController.viewProjectByName();
            case PROJECT_UPDATE_BY_ID: return projectController.updateProjectById();
            case PROJECT_UPDATE_BY_INDEX: return projectController.updateProjectByIndex();
            case PROJECT_UPDATE_BY_NAME: return projectController.updateProjectByName();
            case PROJECT_REMOVE_BY_ID: return projectController.removeProjectById();
            case PROJECT_REMOVE_BY_INDEX: return projectController.removeProjectByIndex();
            case PROJECT_REMOVE_BY_NAME: return projectController.removeProjectByName();

            case TASK_CREATE: return taskController.createTask();
            case TASK_CLEAR: return taskController.clearTask();
            case TASK_LIST: return taskController.listTask();
            case TASK_VIEW_BY_ID: return taskController.viewTaskById();
            case TASK_VIEW_BY_INDEX: return taskController.viewTaskByIndex();
            case TASK_VIEW_BY_NAME: return taskController.viewTaskByName();
            case TASK_UPDATE_BY_ID: return taskController.updateTaskById();
            case TASK_UPDATE_BY_INDEX: return taskController.updateTaskByIndex();
            case TASK_UPDATE_BY_NAME: return taskController.updateTaskByName();
            case TASK_REMOVE_BY_ID: return taskController.removeTaskById();
            case TASK_REMOVE_BY_INDEX: return taskController.removeTaskByIndex();
            case TASK_REMOVE_BY_NAME: return taskController.removeTaskByName();
            case TASK_LIST_BY_PROJECT_ID: return taskController.listTaskByProjectId();
            case TASK_ADD_TO_PROJECT_BY_IDS: return taskController.addTaskToProjectByIds();
            case TASK_REMOVE_FROM_PROJECT_BY_IDS : return taskController.removeTaskFromProjectByIds();

            case USER_CREATE: return userController.createUser();
            case USER_CLEAR: return userController.clearUser();
            case USER_LIST: return userController.listUser();
            case USER_VIEW_BY_ID: return userController.viewUserById();
            case USER_VIEW_BY_INDEX: return userController.viewUserByIndex();
            case USER_VIEW_BY_LOGIN_NAME: return userController.viewUserByLoginName();
            case USER_UPDATE_BY_ID: return userController.updateUserById();
            case USER_UPDATE_BY_INDEX: return userController.updateUserByIndex();
            case USER_UPDATE_BY_LOGIN_NAME: return userController.updateUserByLoginName();
            case USER_REMOVE_BY_ID: return userController.removeUserById();
            case USER_REMOVE_BY_INDEX: return userController.removeUserByIndex();
            case USER_REMOVE_BY_LOGIN_NAME: return userController.removeByLoginName();
            case USER_UPDATE_PASSWORD_BY_ID: return userController.updatePasswordById();
            case USER_UPDATE_PASSWORD_BY_INDEX: return userController.updatePasswordByIndex();
            case USER_UPDATE_PASSWORD_BY_LOGIN_NAME: return userController.updatePasswordByLoginName();
            case USER_UPDATE_ROLE_BY_ID: return userController.updateRoleById();
            case USER_UPDATE_ROLE_BY_INDEX: return userController.updateRoleByIndex();
            case USER_UPDATE_ROLE_BY_LOGIN_NAME: return userController.updateRoleByLoginName();

            case USER_LOGON: {
                sessionId = userController.logonUser();
                return 0;
            }
            case USER_LOGOUT: {
                sessionId = userController.logoutUser();
                return 0;
            }

            case PROJECT_LIST_BY_USER_ID: return projectController.listProjectByUserId();
            case PROJECT_ADD_TO_USER_BY_IDS: return projectController.addProjectToUserByIds();
            case PROJECT_REMOVE_FROM_USER_BY_IDS: return projectController.removeProjectFromUserByIds();

            case TASK_LIST_BY_USER_ID: return taskController.listTaskByUserId();
            case TASK_ADD_TO_USER_BY_IDS: return taskController.addTaskToUserByIds();
            case TASK_REMOVE_FROM_USER_BY_IDS: return taskController.removeTaskFromUserByIds();

            case "test": {
                System.out.println(sessionId);
                if (sessionId != null) System.out.println(userService.findById(sessionId).getLoginName());
                return 0;}

            default: return systemController.displayError(param);
        }
    }

}
