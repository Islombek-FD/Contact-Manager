package uz.practice.service;

import uz.practice.dto.ContactDTO;
import uz.practice.repository.ContactRepository;

import java.util.List;

public class ContactService {
    private final ContactRepository contactRepository = new ContactRepository();

    public void addContact(ContactDTO contactDTO) {
        ContactDTO exists = contactRepository.getByPhone(contactDTO.getPhone());
        // Check phone
        if (exists != null) {
            System.out.println("Phone already exists!");
            return;
        }
        // Save new contact
        boolean response = contactRepository.save(contactDTO);
        if (response) {
            System.out.println("Successfully added!");
        } else {
            System.out.println("Something wrong!");
        }
    }

    public void listContact() {
        List<ContactDTO> contactDTOS = contactRepository.getList();
        for (ContactDTO contactDTO : contactDTOS) {
            System.out.println(contactDTO);
        }
    }

    public void deleteContact(String phone) {
        int response = contactRepository.delete(phone);
        if (response != 0) {
            System.out.println("Successfully deleted!");
        } else {
            System.out.println("Something wrong!");
        }
    }

    public void searchContact(String query) {
        List<ContactDTO> contactDTOS = contactRepository.search(query);
        for (ContactDTO contactDTO : contactDTOS) {
            System.out.println(contactDTO);
        }
    }
}
