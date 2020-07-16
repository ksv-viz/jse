package ru.ksv.tm;

import java.util.Scanner;

import static ru.ksv.tm.constant.TerminalConst.*;

public class App {

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
            default: return displayError(param);
        }
    }

    private static void process() {
        final Scanner scanner = new Scanner(System.in);
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
