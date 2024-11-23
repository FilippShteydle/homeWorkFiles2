package com.shteydle.top.homeWorkFiles2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContactService {

    private static final ContactRepository contactRepository = new ContactRepository();

    public void loadData(String path) throws ContactServiceException {
        List<Contact> workerList = new ArrayList<>();
        int count;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(path))) {
            count = input.readInt();

            while (count > 0) {
                Contact contact = (Contact) input.readObject();
                workerList.add(contact);
                count--;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new ContactServiceException(e);
        }
        contactRepository.save(workerList);
    }

    public void saveAll(String path) throws ContactServiceException {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            List<Contact> contactList = contactRepository.getListContacts();
            outputStream.writeInt(contactList.size());

            for (Contact contact : contactList) {
                outputStream.writeObject(contact);
            }
        } catch (IOException e) {
            throw new ContactServiceException(e);
        }
    }

    public void createContact(String firstName, String lastName, String nickName, String email, int age, Map<String, String> numberMap) {

        contactRepository.createContact(firstName, lastName, nickName, email, age, numberMap);
    }

    public void showContact() {
        List<Contact> contacts = contactRepository.getListContacts();
        for (Contact contact : contacts) {
            System.out.println(contact);
        }

    }

    public List<Contact> findContactOfName(String name) {
       return contactRepository.findContactOfName(name);
    }

    public String checkNumber(String number) {
        return contactRepository.checkNumber(number);
    }
}
