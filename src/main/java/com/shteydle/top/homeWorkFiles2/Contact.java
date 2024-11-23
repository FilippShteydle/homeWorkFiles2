package com.shteydle.top.homeWorkFiles2;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

public class Contact implements Serializable {

    private String firstName;
    private String lastName;
    private String nickName;
    private String email;
    private int yearOfBorn;
    private Map<String, String> numbers;
    //private static final long serialVersionUID = 10000000L;


    public Contact(String firstName, String lastName, String nickName, Map<String, String> number, int yearOfBorn, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        numbers = number;
        this.yearOfBorn = yearOfBorn;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {
        return email;
    }

    public int getYearOfBorn() {
        return yearOfBorn;
    }

    public Map<String, String> getNumbers() {
        return numbers;
    }

    private String printNumbers() {
        StringBuilder s = new StringBuilder("");

        for (Map.Entry<String, String> entry : numbers.entrySet()) {
            s.append(entry.getKey()).append(": ").append(entry.getValue());
        }

        return s.toString();
    }
    @Override
    public String toString() {
        return "Контакт: %s %s, прозвище: %s, %s email: %s, возраст %d".formatted(firstName, lastName, nickName, printNumbers(), email, LocalDate.now().getYear() - yearOfBorn);
    }
}
