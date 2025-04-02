package org.example.contactmanager;

import org.example.contactmanager.database.Database;
import org.example.contactmanager.model.Contact;
import org.example.contactmanager.model.ContactRepository;

import java.util.List;

public class DBTest {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        ContactRepository contactRepository = new ContactRepository();

        contactRepository.addContact("Hans", "1234", "Linz");
        contactRepository.addContact("Anna", "6548", "Wels");
        contactRepository.addContact("David", "06644302232", "Florianistraße 8/5");

        List<Contact> contacts = contactRepository.getAllContact();
        contacts.forEach(System.out::println);
    }
}

