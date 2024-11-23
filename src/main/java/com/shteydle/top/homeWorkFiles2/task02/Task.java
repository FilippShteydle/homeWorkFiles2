package com.shteydle.top.homeWorkFiles2.task02;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Task implements Serializable {

    private static int generatedId = 0;

    private String taskName;
    private String taskDesc;
    private int priority;
    private LocalDate startDate;
    private LocalDate finishDate;
    private String performer;
    private boolean status;
    private int id;

    public Task(String taskName, String taskDesc, int priority, LocalDate finishDate, String performer) {
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.priority = priority;
        this.finishDate = finishDate;
        this.performer = performer;
        this.status = false;
        this.startDate = LocalDate.now();
        id = generatedId++;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getId() {
        return id;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public String getPerformer() {
        return performer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    @Override
    public String toString() {
        return "Задача №: " + id + " - " + taskName + ", необходимо: " + taskDesc + ", приоритет: " + priority +
                ", ответственный: " + performer + ", задача создана: " + startDate + ", выполнить до: " + finishDate +
                ", статус задачи: " + (status ? "выполнена" : "невыполнена");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return priority == task.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(priority);
    }
}
