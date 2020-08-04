package ru.ksv.tm.repository;

import ru.ksv.tm.entity.Project;
import ru.ksv.tm.entity.User;

import java.util.ArrayList;
import java.util.List;

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
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        users.add(user);
        return user;
    }

    public User update(final String loginName, final String lastName, final String firstName, final String middleName, final String description) {
        final User user = findByLoginName(loginName);
        if (user == null) return null;
        user.setLoginName(loginName);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setDescription(description);
        return user;
    }

    public void clear() {
        users.clear();
    }

    public List<User> findAll() {
        return users;
    }

    public User findByLoginName(final String loginName) {
        for (User user : users) {
            if (user.getLoginName().equals(loginName)) return user;
        }
        return null;
    }

    public User removeByLoginName(final String loginName) {
        final User user = findByLoginName(loginName);
        if (user == null) return null;
        users.remove(user);
        return user;
    }

}
