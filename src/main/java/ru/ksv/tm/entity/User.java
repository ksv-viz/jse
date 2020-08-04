package ru.ksv.tm.entity;

public class User {

    private String loginName = "";

    private String passwordHash = "";

    private String firstName = "";

    private String middleName = "";

    private String lastName = "";

    private String description = "";

    public User() {
    }

    public User(String loginName) {
        this.loginName = loginName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return loginName + ": " + lastName + " " + firstName + " " + middleName + " [" + description + "]";
    }

}
