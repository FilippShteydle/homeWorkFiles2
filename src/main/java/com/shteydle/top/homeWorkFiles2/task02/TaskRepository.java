package com.shteydle.top.homeWorkFiles2.task02;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskRepository {

    private static Map<Integer, Task> tasks = new HashMap<>();

    public void addTask(Task task) {

        tasks.put(task.getId(), task);
    }

    public void save(List<Task> taskList) {
        for (Task task : taskList) {
            tasks.put(task.getId(), task);
        }
    }

    public List<Task> getListTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void print() {
        for (Task task : tasks.values()) {
            System.out.println(task);
        }
    }

    public void reStatus(int i) {
        Task task = tasks.get(i);
        task.setStatus(true);
    }
}
