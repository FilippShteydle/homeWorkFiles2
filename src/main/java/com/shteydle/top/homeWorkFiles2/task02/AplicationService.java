package com.shteydle.top.homeWorkFiles2.task02;

import java.util.Scanner;

public class AplicationService {

    private static TaskService taskService = new TaskService();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        String line;
        System.out.println("Введите путь со списком задач: ");
        String path = scanner.nextLine();

        try {
            taskService.loadData(path);
        } catch (TaskServiceException e) {
            System.out.println(e.getMessage());
        }

        do {
            System.out.println("""
                Выберите действие:
                1. Добавить задачу;
                2. Просмотреть все задачи;
                3. Поменять статус задания на выполненный;
                4. Сохранить задачи;
                0. Сохранить и выйти.
                """);

            line = scanner.nextLine();
            switch (line) {
                case "1" -> addTask();

                case "2" -> printTask();

                case "3" -> reStatus();

                case "4", "0" -> save(path);
            }
        } while (!line.equals("0"));
        scanner.close();
    }

    private void save(String path) {
        try {
            taskService.saveAll(path);
        } catch (TaskServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void reStatus() {
        System.out.println("Введите номер задания: ");
        String id = scanner.nextLine();
        taskService.reStatus(Integer.parseInt(id));
    }

    private void printTask() {
        taskService.print();
    }

    private void addTask() {
        System.out.println("Введите название задачи:");
        String name = scanner.nextLine();
        System.out.println("Введите описание задачи:");
        String description = scanner.nextLine();
        System.out.println("Определите приоритет задачи от 1 до 10, где 1 - очень срочная, 10 - не срочная:");
        String priority = scanner.nextLine();
        System.out.println("Введите имя исполнителя задачи:");
        String namePerfomer = scanner.nextLine();
        System.out.println("Введите дату, до которой необходимо выполнить задачу:");
        String finishDate = scanner.nextLine();
        taskService.addTask(name, description, priority, namePerfomer, finishDate);
    }
}
