package ru.ksv.tm.service;

import ru.ksv.tm.entity.User;
import ru.ksv.tm.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String loginName) {
        return userRepository.create(loginName);
    }

    public User create(String loginName, String lastName, String firstName, String middleName) {
        return userRepository.create(loginName, lastName, firstName, middleName);
    }

    public User update(String loginName, String lastName, String firstName, String middleName, String description) {
        return userRepository.update(loginName, lastName, firstName, middleName, description);
    }

    public void clear() {
        userRepository.clear();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByLoginName(String loginName) {
        return userRepository.findByLoginName(loginName);
    }

    public User removeByLoginName(String loginName) {
        return userRepository.removeByLoginName(loginName);
    }
}
