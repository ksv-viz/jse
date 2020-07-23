package ru.ksv.tm.dao;

import ru.ksv.tm.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    private final List<Task> tasks = new ArrayList<>();

    public Task create(final String name) {
        final Task task = new Task(name);
        tasks.add(task);
        return task;
    }

    public void clear() {
        tasks.clear();
    }

    public List<Task> findAll()
    {
        return tasks;
    }

}
