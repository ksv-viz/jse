package ru.ksv.tm.enumerated;

public enum Role {
    ADMIN("Administrator"),
    USER("User"),
    QIEST("Quest");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

}
