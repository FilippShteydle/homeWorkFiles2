package com.shteydle.top.homeWorkFiles2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactRepository {

    private static Map<String, Contact> phoneBook = new HashMap<>();


    public void save(List<Contact> workerList) {
        for (Contact c : workerList) {
            phoneBook.put(c.getLastName() + " " + c.getFirstName() + " " + c.getNickName(), c);
        }
    }

    public List<Contact> getListContacts() {
        List<Contact> contactList = new ArrayList<>();
        for (Contact c : phoneBook.values()) {
            contactList.add(c);
        }
        return contactList;
    }

    public void createContact(String firstName, String lastName, String nickName, String email, int age, Map<String, String> numberMap) {
        phoneBook.put(lastName + " " + firstName + " " + nickName, new Contact(firstName, lastName, nickName, numberMap, age, email));
    }

    public List<Contact> findContactOfName(String name) {
        String[] names = name.split(" ");
        List<Contact> contact = new ArrayList<>();
        for (Contact c : phoneBook.values()) {
            if (c.getLastName().equals(names[0]) && c.getFirstName().equals(names[1])) {
                contact.add(c);
            }
        }
        return contact;
    }

    public String checkNumber(String number) {
        String result = "";
        boolean flag = false;
        for (Contact c : phoneBook.values()) {
            for (String s : c.getNumbers().values()) {
                if (s.contains(number)) {
                    result = "Данный номер принадлежит контакту: " + c.getLastName() + " " + c.getFirstName() + " " + c.getNickName();
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }

        return result;
    }
}
