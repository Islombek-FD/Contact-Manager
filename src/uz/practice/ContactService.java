package uz.practice;

import java.util.List;

public class ContactService {

    public void addContact(Contact contact) {
        ContactRepository contactRepository = new ContactRepository();
        Contact exists = contactRepository.getByPhone(contact.getPhone());
        // Check phone
        if (exists != null) {
            System.out.println("Phone already exists!");
            return;
        }
        // Save new contact
        boolean response = contactRepository.save(contact);
        if (response) {
            System.out.println("Successfully added!");
        } else {
            System.out.println("Something wrong!");
        }
    }

    public void listContact() {
        ContactRepository contactRepository = new ContactRepository();
        List<Contact> contacts = contactRepository.getList();
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public void deleteContact(String phone) {
        ContactRepository contactRepository = new ContactRepository();
        int response = contactRepository.delete(phone);
        if (response != 0) {
            System.out.println("Successfully deleted!");
        } else {
            System.out.println("Something wrong!");
        }
    }

    public void searchContact(String query) {
        ContactRepository contactRepository = new ContactRepository();
        List<Contact> contacts = contactRepository.search(query);
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}
