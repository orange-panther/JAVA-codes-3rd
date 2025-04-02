package org.example.contactmanager;

import org.example.contactmanager.database.Database;
import org.example.contactmanager.model.Contact;
import org.example.contactmanager.model.ContactRepository;

import java.util.List;

public class DBTest {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        ContactRepository contactRepository = new ContactRepository();

        List<Contact> contacts = contactRepository.getAllContact();
        contacts.forEach(System.out::println);
    }
}

