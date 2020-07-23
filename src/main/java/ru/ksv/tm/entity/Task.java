package ru.ksv.tm.entity;

public class Task {

    private Long id = System.nanoTime();

    private String name = "";

    private String description = "";

    public Long getId() {
        return id;
    }

    public Task() {
    }

    public Task(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return id + ": '" + name + "'";
    }

}
