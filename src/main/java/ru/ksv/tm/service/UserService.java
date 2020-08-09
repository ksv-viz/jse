package ru.ksv.tm.service;

import ru.ksv.tm.entity.User;
import ru.ksv.tm.enumerated.Role;
import ru.ksv.tm.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String loginName) {
        if (loginName == null || loginName.isEmpty()) return null;
        return userRepository.create(loginName);
    }

    public User create(String loginName, String lastName, String firstName, String middleName) {
        if (loginName == null || loginName.isEmpty()) return null;
        return userRepository.create(loginName, lastName, firstName, middleName);
    }

    public User create(String loginName, String lastName, String firstName, String middleName, String description) {
        if (loginName == null || loginName.isEmpty()) return null;
        return userRepository.create(loginName, lastName, firstName, middleName, description);
    }

    public User update(Long id, String loginName, String firstName, String middleName, String lastName, String description) {
        if (id == null) return null;
        if (loginName == null || loginName.isEmpty()) return null;
        return userRepository.update(id, loginName, firstName, middleName, lastName, description);
    }

    public void clear() {
        userRepository.clear();
    }

    public User updatePasswordByLoginName(String loginName, String password) {
        if (loginName == null || loginName.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        return userRepository.updatePasswordByLoginName(loginName, password);
    }

    public User updatePasswordById(Long id, String password) {
        if (id == null) return null;
        if (password == null || password.isEmpty()) return null;
        return userRepository.updatePasswordById(id, password);
    }

    public User updatePasswordByIndex(int index, String password) {
        if (index < 0 || index > userRepository.findAll().size() - 1) return null;
        if (password == null || password.isEmpty()) return null;
        return userRepository.updatePasswordByIndex(index, password);
    }

    public User updateRoleByLoginName(String loginName, Role role) {
        if (loginName == null || loginName.isEmpty()) return null;
        if (role == null) return null;
        return userRepository.updateRoleByLoginName(loginName, role);
    }

    public User updateRoleById(Long id, Role role) {
        if (id == null) return null;
        if (role == null) return null;
        return userRepository.updateRoleById(id, role);
    }

    public User updateRoleByIndex(int index, Role role) {
        if (index < 0 || index > userRepository.findAll().size() - 1) return null;
        if (role == null) return null;
        return userRepository.updateRoleByIndex(index, role);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByLoginName(String loginName) {
        if (loginName == null || loginName.isEmpty()) return null;
        return userRepository.findByLoginName(loginName);
    }

    public User findById(Long id) {
        if (id == null) return null;
        return userRepository.findById(id);
    }

    public User findByIndex(int index) {
        if (index < 0 || index > userRepository.findAll().size() - 1) return null;
        return userRepository.findByIndex(index);
    }

    public User removeByLoginName(String loginName) {
        if (loginName == null || loginName.isEmpty()) return null;
        return userRepository.removeByLoginName(loginName);
    }

    public User removeById(Long id) {
        if (id == null) return null;
        return userRepository.removeById(id);
    }

    public User removeByIndex(int index) {
        if (index < 0 || index > userRepository.findAll().size() - 1) return null;
        return userRepository.removeByIndex(index);
    }
}
