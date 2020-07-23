package ru.ksv.tm;

import ru.ksv.tm.dao.ProjectDAO;
import ru.ksv.tm.dao.TaskDAO;

import java.util.Scanner;

import static ru.ksv.tm.constant.TerminalConst.*;

public class App {

    private static final ProjectDAO projectDAO = new ProjectDAO();

    private static final TaskDAO taskDAO = new TaskDAO();

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(final String[] args) {
        run(args);
        displayWelcome();
        process();
    }

    private static void run (final String[] args) {
        if (args == null) return;
        if (args.length < 1) return;
        final String param = args[0];
        final int  result = run(param);
        System.exit(result);
    }

    private static int run (final String param) {
        if (param == null || param.isEmpty()) return -1;
        switch (param) {
            case VERSION: return displayVersion();
            case ABOUT: return displayAbout();
            case HELP: return displayHelp ();
            case EXIT: return exit();

            case PROJECT_CREATE: return createProject();
            case PROJECT_CLEAR: return clearProject();
            case PROJECT_LIST: return listProject();

            case TASK_CREATE: return createTask();
            case TASK_CLEAR: return clearTask();
            case TASK_LIST: return listTask();

            default: return displayError(param);
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
        System.out.println(projectDAO.findAll());
        System.out.println("[OK]");
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
        System.out.println(taskDAO.findAll());
        System.out.println("[OK]");
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
        System.out.println();
        System.out.println("task-create - Create new task by name.");
        System.out.println("task-list - Display list of tasks.");
        System.out.println("task-clear - Remove all tasks.");
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
