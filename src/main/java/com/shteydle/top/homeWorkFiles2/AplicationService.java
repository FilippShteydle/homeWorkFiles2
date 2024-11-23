package com.shteydle.top.homeWorkFiles2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AplicationService {

    private Scanner scanner = new Scanner(System.in);
    private ContactService contactService = new ContactService();

    public void run() {

        System.out.println("Введите путь к телефонной книге:");
        String path = scanner.nextLine();
        try {
            contactService.loadData(path);
        } catch (ContactServiceException e) {
            System.out.println(e.getMessage());
        }
        String line;
        do {
            System.out.println("""
                    Выберите действие:
                    1. Создать контакт;
                    2. Просмотреть все контакты;
                    3. Найти контакт по имени;
                    4. Найти контакт по номеру телефона;
                    5. Сохранить контакты;
                    0. Выйти из программы.""");

            line = scanner.nextLine();
            switch (line) {

                case "1" -> createContact();

                case "2" -> showContact();

                case "3" -> findContactOfName();

                case "4" -> findContactOfNumber();

                case "5", "0" -> save(path);
            }
        } while (!"0".equals(line));

        scanner.close();
    }

    private void findContactOfNumber() {

        System.out.println("Введите номер телефона: ");
        String number = scanner.nextLine();
        System.out.println(checkNumber(number));
    }

    private void findContactOfName() {

        System.out.println("Введите фамилию и имя контакта:");
        String name = scanner.nextLine();
        List<Contact> contacts = contactService.findContactOfName(name);

        if (contacts.size() > 0) {
            System.out.println(contacts);
        } else {
            System.out.println("Такого контакта не существует");
        }

    }

    private void showContact() {

        contactService.showContact();
    }

    private void save(String path) {
        try {
            contactService.saveAll(path);
        } catch (ContactServiceException e) {
            System.out.println(e.getMessage());;
        }
    }

    private void createContact() {
        System.out.println("Введите фамилию, имя и прозвище контакта через пробел:");
        String name = scanner.nextLine();
        boolean flag = false;
        String email = "";
        while (!flag) {
            System.out.println("Введите электронную почту контакта:");
            email = scanner.nextLine();
            flag = checkEmail(email);
            if (!flag) {
                System.out.println("Неверно введен Email");
            }
        }
        System.out.println("Введите год рождения контакта: ");
        int age = Integer.parseInt(scanner.nextLine());
        String line;
        String number;
        List<String> numbers = new ArrayList<>();;
        String[] fullNames = name.split(" ");
        do {
            System.out.println("""
                    1. Добавить домашний телефон;
                    2. Добавить рабочий телефон;
                    3. Добавить мобильный телефон;
                    4. Добавить факс;
                    0. Сохранить контакт;""");

            line = scanner.nextLine();
            switch (line) {

                case "1" -> {
                    System.out.println("Введите номер телефона:");
                    number = scanner.nextLine();
                    if (checkNumber(number).equals("")) {
                        numbers.add("домашний");
                        numbers.add(number);
                    } else {
                        System.out.println("Номер не сохранен. " + checkNumber(number));
                    }

                }

                case "2" -> {
                    System.out.println("Введите номер телефона:");
                    number = scanner.nextLine();
                    if (checkNumber(number).equals("")) {
                        numbers.add("рабочий");
                        numbers.add(number);
                    } else {
                        System.out.println("Номер не сохранен. " + checkNumber(number));
                    }
                }

                case "3" -> {
                    System.out.println("Введите номер телефона:");
                    number = scanner.nextLine();
                    if (checkNumber(number).equals("")) {
                        numbers.add("мобильный");
                        numbers.add(number);
                    } else {
                        System.out.println("Номер не сохранен. " + checkNumber(number));
                    }
                }

                case "4" -> {
                    System.out.println("Введите номер телефона:");
                    number = scanner.nextLine();
                    if (checkNumber(number).equals("")) {
                        numbers.add("факс");
                        numbers.add(number);
                    } else {
                        System.out.println("Номер не сохранен. " + checkNumber(number));
                    }
                }
            }
        } while (!"0".equals(line));

        contactService.createContact(fullNames[1], fullNames[0], fullNames[2], email, age, addNumber(numbers));
    }

    private boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)\\.\\w{2,4}");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private String checkNumber(String number) {
        return contactService.checkNumber(number);
    }

    private Map<String, String> addNumber(List<String> numbers) {
        Map<String, String> map = new HashMap<>();
        StringBuilder homeNumbers = new StringBuilder(), workNumbers = new StringBuilder(),
                mobileNumbers = new StringBuilder(), faxNumbers = new StringBuilder();
        String homeNum = "домашний";
        String workNum = "рабочий";
        String mobileNum = "мобильный";
        String faxNum = "факс";
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i).equals(homeNum)) {
                homeNumbers.append(numbers.get(i + 1)).append(", ");
            } else if (numbers.get(i).equals(workNum)) {
                workNumbers.append(numbers.get(i + 1)).append(", ");
            } else if (numbers.get(i).equals(mobileNum)) {
                mobileNumbers.append(numbers.get(i + 1)).append(", ");
            } else if (numbers.get(i).equals(faxNum)){
                faxNumbers.append(numbers.get(i + 1)).append(", ");
            }
        }
        if (!homeNumbers.isEmpty()) map.put(homeNum, homeNumbers.toString());
        if (!workNumbers.isEmpty()) map.put(workNum, workNumbers.toString());
        if (!mobileNumbers.isEmpty()) map.put(mobileNum, mobileNumbers.toString());
        if (!faxNumbers.isEmpty()) map.put(faxNum, faxNumbers.toString());

        return map;
    }
}
