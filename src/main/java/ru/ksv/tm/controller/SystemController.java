package ru.ksv.tm.controller;

public class SystemController {

    public void displayWelcome() {
        System.out.println("** WELCOME TO TASK MANAGER **");
    }

    public int displayVersion() {
        System.out.println("1.0.0.1");
        return 0;
    }

    public int displayAbout() {
        System.out.println("Serg V. Kazakov");
        System.out.println("serg.v.kazakov@mail.ru");
        return 0;
    }

    public int displayHelp() {
        System.out.println("user-logon - Logon user to application");
        System.out.println("user-logout - Logout user from application");
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
        System.out.println("task-list-by-project-id - Display task list by project id");
        System.out.println("task-add-to-project-by-ids - Add task to project by ids");
        System.out.println("task-remove-form-project-by-ids - Remove task from project by id");
        System.out.println();
        System.out.println("user-create - Create new user.");
        System.out.println("user-clear - Remove all users.");
        System.out.println("user-list - Display list of users.");
        System.out.println("user-view-current - View current user.");
        System.out.println("user-view-by-id - View user by id.");
        System.out.println("user-view-by-index - View user by index.");
        System.out.println("user-view-by-login-name - View user by login name.");
        System.out.println("user-remove-by-id - Remove user by id.");
        System.out.println("user-remove-by-index - Remove user by index.");
        System.out.println("user-remove-by-login-name - Remove user by login name.");
        System.out.println("user-update-by-id - Update user by id.");
        System.out.println("user-update-by-index - Update user by index.");
        System.out.println("user-update-by-login-name - Update user by login name.");
        System.out.println("user-update-password - Update password by current user.");
        System.out.println("user-update-password-by-id - Update user password by id.");
        System.out.println("user-update-password-by-index - Update user password by index.");
        System.out.println("user-update-password-by-login-name - Update password user by login name.");
        System.out.println("user-update-role-by-id - Update user role by id.");
        System.out.println("user-update-role-by-index - Update user role by index.");
        System.out.println("user-update-role-by-login-name - Update user role by login name.");
        System.out.println();
        System.out.println("project-list-by-user-id - Display project list by user id");
        System.out.println("project-add-to-user-by-ids - Add project to user by ids");
        System.out.println("project-remove-from-user-by-ids - Remove project from user by id");
        System.out.println("task-list-by-user-id - Display task list by user id");
        System.out.println("task-add-to-user-by-ids - Add task to user by ids");
        System.out.println("task-remove-from-user-by-ids - Remove task from user by id");

        return 0;
    }

    public int displayError(final String errorparam) {
        System.out.println("Error! Unknown [" + errorparam + "] program argument...");
        System.out.println("Try one of this:");
        displayHelp();
        return -1;
    }

    public int displayExit() {
        System.out.println("Application terminate...");
        System.exit(0);
        return 0;
    }

}
