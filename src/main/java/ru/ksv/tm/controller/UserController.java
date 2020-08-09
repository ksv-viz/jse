package ru.ksv.tm.controller;

import ru.ksv.tm.entity.User;
import ru.ksv.tm.enumerated.Role;
import ru.ksv.tm.service.UserService;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

public class UserController extends AbstractController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public int logonUser() {
        System.out.println("PLEASE, ENTER USER LOGIN:");
        final String loginName = scanner.nextLine();
        System.out.println("PLEASE, ENTER USER PASSWORD:");
        final String loginPassword = scanner.nextLine();
        if (userService.findByLoginName(loginName) == null) return -1;
        if (!userService.findByLoginName(loginName).getPasswordHash().equals(DigestUtils.md5Hex(loginPassword))) return -1;
        return 0;
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
        int index = 1;
        for (final User user : userService.findAll()) {
            System.out.println(index + ". " + user.getId() + ": " + user.getLoginName()+" ["+ user.getUserRole().getDisplayName()+"]");
            index++;
        }
        System.out.println("[OK]");
        return 0;
    }

    public void viewUser(final User user) {
        if (user == null) return;
        System.out.println("[VIEW USER]");
        System.out.println("ID: " + user.getId());
        System.out.println("LOGIN: " + user.getLoginName());
        System.out.println("ROLE: " + user.getUserRole().getDisplayName());
        System.out.println("FIRST NAME: " + user.getFirstName());
        System.out.println("MIDDLE NAME: " + user.getMiddleName());
        System.out.println("LAST NAME: " + user.getLastName());
        System.out.println("DESCRIPTION: " + user.getDescription());
        System.out.println("[OK]");
    }

    public int viewUserById() {
        System.out.println("[ENTER, USER ID]");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long id = scanner.nextLong();
        final User user = userService.findById(id);
        viewUser(user);
        return 0;
    }

    public int viewUserByIndex() {
        System.out.println("[ENTER, USER INDEX]");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final int index = scanner.nextInt() - 1;
        final User user = userService.findByIndex(index);
        viewUser(user);
        return 0;
    }

    public int viewUserByLoginName() {
        System.out.println("[ENTER, USER NAME]");
        final String name = scanner.nextLine();
        final User user = userService.findByLoginName(name);
        viewUser(user);
        return 0;
    }

    public int updateUserById() {
        System.out.println("[UPDATE USER BY ID]");
        System.out.println("PLEASE, ENTER USER ID");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long id = scanner.nextLong();
        scanner.nextLine();
        final User user = userService.findById(id);
        if (user == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER USER LOGIN:");
        final String loginName = scanner.nextLine();
        System.out.println("PLEASE, ENTER USER LAST NAME:");
        final String lastName = scanner.nextLine();
        System.out.println("PLEASE, ENTER USER FIRST NAME:");
        final String firstName = scanner.nextLine();
        System.out.println("PLEASE, ENTER USER MIDDLE NAME:");
        final String middleName = scanner.nextLine();
        System.out.println("PLEASE, ENTER USER DESCRIPTION:");
        final String description = scanner.nextLine();
        userService.update(user.getId(), loginName, firstName, middleName, lastName, description);
        System.out.println("[OK]");
        return 0;
    }

    public int updateUserByIndex() {
        System.out.println("[UPDATE USER BY INDEX]");
        System.out.println("PLEASE, ENTER USER INDEX");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final int index = scanner.nextInt() - 1;
        scanner.nextLine();
        final User user = userService.findByIndex(index);
        if (user == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER USER LOGIN:");
        final String loginName = scanner.nextLine();
        System.out.println("PLEASE, ENTER USER LAST NAME:");
        final String lastName = scanner.nextLine();
        System.out.println("PLEASE, ENTER USER FIRST NAME:");
        final String firstName = scanner.nextLine();
        System.out.println("PLEASE, ENTER USER MIDDLE NAME:");
        final String middleName = scanner.nextLine();
        System.out.println("PLEASE, ENTER USER DESCRIPTION:");
        final String description = scanner.nextLine();
        userService.update(user.getId(), loginName, firstName, middleName, lastName, description);
        System.out.println("[OK]");
        return 0;
    }

    public int updateUserByLoginName() {
        System.out.println("[UPDATE USER BY LOGIN NAME]");
        System.out.println("PLEASE, ENTER USER LOGIN");
        final String findingLoginName = scanner.nextLine();
        final User user = userService.findByLoginName(findingLoginName);
        if (user == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER USER NEW LOGIN:");
        System.out.println("IF EMPTY - NO CHANGE:");
        final String loginName = scanner.nextLine();
        System.out.println("PLEASE, ENTER USER LAST NAME:");
        final String lastName = scanner.nextLine();
        System.out.println("PLEASE, ENTER USER FIRST NAME:");
        final String firstName = scanner.nextLine();
        System.out.println("PLEASE, ENTER USER MIDDLE NAME:");
        final String middleName = scanner.nextLine();
        System.out.println("PLEASE, ENTER USER DESCRIPTION:");
        final String description = scanner.nextLine();
        userService.update(user.getId(), loginName, firstName, middleName, lastName, description);
        System.out.println("[OK]");
        return 0;
    }

    public int removeUserById() {
        System.out.println("[REMOVE USER BY ID]");
        System.out.println("PLEASE, ENTER USER ID");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long id = scanner.nextLong();
        scanner.nextLine();
        final User user = userService.removeById(id);
        if (user == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
        return 0;
    }

    public int removeUserByIndex() {
        System.out.println("[REMOVE USER BY INDEX]");
        System.out.println("PLEASE, ENTER USER INDEX");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final int index = scanner.nextInt() - 1;
        scanner.nextLine();
        final User user = userService.removeByIndex(index);
        if (user == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
        return 0;
    }

    public int removeByLoginName() {
        System.out.println("[REMOVE USER BY LOGIN]");
        System.out.println("PLEASE, ENTER USER LOGIN");
        final String loginName = scanner.nextLine();
        final User user = userService.removeByLoginName(loginName);
        if (user == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
        return 0;
    }

    public int updatePasswordByLoginName() {
        System.out.println("[UPDATE USER PASSWORD BY LOGIN]");
        System.out.println("PLEASE, ENTER USER LOGIN");
        final String loginName = scanner.nextLine();
        final User user = userService.findByLoginName(loginName);
        if (user == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("ENTER NEW PASSWORD");
        final String password = scanner.nextLine();
        userService.updatePasswordByLoginName(loginName, password);
        return 0;
    }

    public int updatePasswordById() {
        System.out.println("[UPDATE USER PASSWORD BY ID]");
        System.out.println("PLEASE, ENTER USER ID");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long id = scanner.nextLong();
        scanner.nextLine();
        final User user = userService.findById(id);
        if (user == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("ENTER NEW PASSWORD");
        final String password = scanner.nextLine();
        userService.updatePasswordById(id, password);
        return 0;
    }

    public int updatePasswordByIndex() {
        System.out.println("[UPDATE USER PASSWORD BY INDEX]");
        System.out.println("PLEASE, ENTER USER INDEX");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final int index = scanner.nextInt() - 1;
        scanner.nextLine();
        final User user = userService.findByIndex(index);
        if (user == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("ENTER NEW PASSWORD");
        final String password = scanner.nextLine();
        userService.updatePasswordByIndex(index, password);
        return 0;
    }

    public int updateRoleByLoginName() {
        System.out.println("[UPDATE USER ROLE BY LOGIN]");
        System.out.println("PLEASE, ENTER USER LOGIN");
        final String loginName = scanner.nextLine();
        final User user = userService.findByLoginName(loginName);
        if (user == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER NEW USER ROLE");
        System.out.println(Arrays.toString(Role.values()));
        final String roleName = scanner.nextLine();
        Role userRole = user.getUserRole();
        for(Role role : Role.values()) {
            if( roleName.equalsIgnoreCase(role.getDisplayName())) {
                userRole = role;
                break;
            }
        }
        userService.updateRoleByLoginName(loginName, userRole);
        return 0;
    }

    public int updateRoleById() {
        System.out.println("[UPDATE USER ROLE BY ID]");
        System.out.println("PLEASE, ENTER USER ID");
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final Long id = scanner.nextLong();
        scanner.nextLine();
        final User user = userService.findById(id);
        if (user == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER NEW USER ROLE");
        System.out.println(Arrays.toString(Role.values()));
        final String roleName = scanner.nextLine();
        Role userRole = user.getUserRole();
        for(Role role : Role.values()) {
            if( roleName.equalsIgnoreCase(role.getDisplayName())) {
                userRole = role;
                break;
            }
        }
        userService.updateRoleById(id, userRole);
        return 0;
    }

    public int updateRoleByIndex() {
        System.out.println("[UPDATE USER ROLE BY INDEX]");
        System.out.println("PLEASE, ENTER USER INDEX");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("FAIL! IT'S NOT NUMBER");
            return -1;
        }
        final int index = scanner.nextInt() - 1;
        scanner.nextLine();
        final User user = userService.findByIndex(index);
        if (user == null) {
            System.out.println("[FAIL]");
            return 0;
        }
        System.out.println("PLEASE, ENTER NEW USER ROLE");
        System.out.println(Arrays.toString(Role.values()));
        final String roleName = scanner.nextLine();
        Role userRole = user.getUserRole();
        for(Role role : Role.values()) {
            if( roleName.equalsIgnoreCase(role.getDisplayName())) {
                userRole = role;
                break;
            }
        }
        userService.updateRoleByIndex(index, userRole);
        return 0;
    }

}
