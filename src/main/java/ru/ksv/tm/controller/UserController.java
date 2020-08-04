package ru.ksv.tm.controller;

import ru.ksv.tm.entity.User;
import ru.ksv.tm.service.UserService;

public class UserController extends AbstractController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public int createUser() {
        System.out.println("[CREATE USER]");
        System.out.println("PLEASE, ENTER USER LOGIN:");
        final String loginName = scanner.nextLine();
        userService.create(loginName);
        System.out.println("[OK]");
        return 0;
    }

    public int clearUser() {
        System.out.println("[CLEAR USER]");
        userService.clear();
        System.out.println("[OK]");
        return 0;
    }

    public int listUser() {
        System.out.println("[LIST USER]");
        for (final User user : userService.findAll()) {
            System.out.println(user.getLoginName() + ": " + user.getLastName() + " " + user.getFirstName() + " " + user.getMiddleName() + " [" + user.getDescription() + "]");
        }
        System.out.println("[OK]");
        return 0;
    }

    public int updateUser() {
        System.out.println("[UPDATE USER]");
        System.out.println("PLEASE, ENTER USER LOGIN");
        final String loginName = scanner.nextLine();
        final User user = userService.findByLoginName(loginName);
        if (user == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER USER LASTNAME:");
        final String lastName = scanner.nextLine();
        System.out.println("PLEASE, ENTER USER FIRSTNAME:");
        final String firstName = scanner.nextLine();
        System.out.println("PLEASE, ENTER USER MIDDLENAME:");
        final String middleName = scanner.nextLine();
        System.out.println("PLEASE, ENTER USER DESCRIPTION:");
        final String description = scanner.nextLine();
        userService.update(loginName, lastName, firstName, middleName, description);
        System.out.println("[OK]");
        return 0;
    }

    public int removeUser() {
        System.out.println("[REMOVE USER BY LOGIN]");
        System.out.println("PLEASE, ENTER USER LOGIN");
        final String loginName = scanner.nextLine();
        final User user = userService.removeByLoginName(loginName);
        if (user == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
        return 0;
    }

}
