package ru.ksv.tm.repository;

import ru.ksv.tm.entity.User;
import ru.ksv.tm.enumerated.Role;

import ru.ksv.tm.utils.SaltHash;

import java.util.ArrayList;
import java.util.List;

import static ru.ksv.tm.constant.TerminalConst.*;

public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public User create(final String loginName) {
        final User user = new User(loginName);
        users.add(user);
        return user;
    }

    public User create(final String loginName, final String lastName, final String firstName, final String middleName) {
        final User user = new User();
        user.setLoginName(loginName);
        user.setPasswordHash(SaltHash.getHash(DEFAULT_PASSWORD));
        user.setUserRole(DEFAULT_ROLE);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        users.add(user);
        return user;
    }

    public User create(final String loginName, final String lastName, final String firstName, final String middleName, final String description) {
        final User user = new User();
        user.setLoginName(loginName);
        user.setPasswordHash(SaltHash.getHash(DEFAULT_PASSWORD));
        user.setUserRole(DEFAULT_ROLE);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setDescription(description);
        users.add(user);
        return user;
    }

    public User update(final Long id, final String loginName, final String firstName, final String middleName, final String lastName, final String description) {
        final User user = findById(id);
        if (user == null) return null;
        user.setLoginName(loginName);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setDescription(description);
        return user;
    }

    public User updatePasswordByLoginName(final String loginName, final String password) {
        final User user = findByLoginName(loginName);
        if (user == null) return null;
        user.setPasswordHash(SaltHash.getHash(password));
        return user;
    }

    public User updatePasswordById(final Long id, final String password) {
        final User user = findById(id);
        if (user == null) return null;
        user.setPasswordHash(SaltHash.getHash(password));
        return user;
    }

    public User updatePasswordByIndex(final int index, final String password) {
        final User user = findByIndex(index);
        if (user == null) return null;
        user.setPasswordHash(SaltHash.getHash(password));
        return user;
    }

    public User updateRoleByLoginName(final String loginName, final Role role) {
        final User user = findByLoginName(loginName);
        if (user == null) return null;
        user.setUserRole(role);
        return user;
    }

    public User updateRoleById(final Long id, final Role role) {
        final User user = findById(id);
        if (user == null) return null;
        user.setUserRole(role);
        return user;
    }

    public User updateRoleByIndex(final int index, final Role role) {
        final User user = findByIndex(index);
        if (user == null) return null;
        user.setUserRole(role);
        return user;
    }

    public void clear() {
        users.clear();
    }

    public User findById(final Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) return user;
        }
        return null;
    }

    public User findByLoginName(final String loginName) {
        for (User user : users) {
            if (user.getLoginName().equals(loginName)) return user;
        }
        return null;
    }

    public User findByIndex(final int index) {
        return users.get(index);
    }

    public User removeById(final Long id) {
        final User user = findById(id);
        if (user == null) return null;
        users.remove(user);
        return user;
    }

    public User removeByIndex(final int index) {
        final User user = findByIndex(index);
        if (user == null) return null;
        users.remove(user);
        return user;
    }

    public User removeByLoginName(final String loginName) {
        final User user = findByLoginName(loginName);
        if (user == null) return null;
        users.remove(user);
        return user;
    }

    public List<User> findAll() {
        return users;
    }

}
