package com.shteydle.top.homeWorkFiles2.task02;

import com.shteydle.top.homeWorkFiles2.Contact;
import com.shteydle.top.homeWorkFiles2.ContactServiceException;

import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private TaskRepository taskRepository = new TaskRepository();

    public void loadData(String path) throws TaskServiceException {
        List<Task> taskList = new ArrayList<>();
        int count;
        int generatedidInitialVal;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(path))) {
            count = input.readInt();

            generatedidInitialVal = input.readInt();
            Field field = Task.class.getDeclaredField("generatedId");
            field.setAccessible(true);
            field.setInt(null, generatedidInitialVal + 1);

            while (count > 0) {
                Task task = (Task) input.readObject();
                taskList.add(task);
                count--;
            }
        } catch (IOException | ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            throw new TaskServiceException(e);
        }
        taskRepository.save(taskList);
    }

    public void saveAll(String path) throws TaskServiceException {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            List<Task> tasktList = taskRepository.getListTasks();
            outputStream.writeInt(tasktList.size());
            outputStream.writeInt(tasktList.getLast().getId());

            for (Task task : tasktList) {
                outputStream.writeObject(task);
            }
        } catch (IOException e) {
            throw new TaskServiceException(e);
        }
    }

    public void addTask(String name, String description, String priority, String namePerfomer, String finishDate) {
        String[] date = finishDate.split("\\.");
        taskRepository.addTask(new Task(name, description, Integer.parseInt(priority),
                LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0])), namePerfomer));
    }

    public void print() {
        taskRepository.print();
    }

    public void reStatus(int i) {
        taskRepository.reStatus(i);
    }
}
