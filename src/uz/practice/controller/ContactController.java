package uz.practice.controller;

import uz.practice.dto.ContactDTO;
import uz.practice.service.ContactService;
import uz.practice.utils.DBUtils;

import java.util.Scanner;

public class ContactController {
    private final ContactService contactService = new ContactService();
    private final Scanner numberScanner = new Scanner(System.in);
    private final Scanner stringScanner = new Scanner(System.in);

    public void start() {
        DBUtils.createTable();

        boolean b = true;
        while (b) {
            showMenu();
            int action = getAction();
            switch (action) {
                case 0 -> b = false;
                case 1 -> addContact();
                case 2 -> listContact();
                case 3 -> deleteContact();
                case 4 -> searchContact();
            }
        }
    }

    public void showMenu() {
        System.out.println("*** MENU ***");
        System.out.println("0. EXIT");
        System.out.println("1. ADD CONTACT");
        System.out.println("2. CONTACT LIST ");
        System.out.println("3. DELETE CONTACT");
        System.out.println("4. SEARCH CONTACT");
    }

    public int getAction() {
        System.out.print("Enter action: ");
        return numberScanner.nextInt();
    }

    public void addContact() {
        System.out.print("Enter name: ");
        String name = stringScanner.nextLine();

        System.out.print("Enter surname: ");
        String surname = stringScanner.nextLine();

        System.out.print("Enter phone: ");
        String phone = stringScanner.nextLine();

        ContactDTO contactDTO = new ContactDTO();

        contactDTO.setName(name);
        contactDTO.setSurname(surname);
        contactDTO.setPhone(phone);

        contactService.addContact(contactDTO);
    }

    public void listContact() {
        contactService.listContact();
    }

    public void deleteContact() {
        System.out.print("Enter phone: ");
        String phone = stringScanner.nextLine();

        contactService.deleteContact(phone);
    }

    public void searchContact() {
        System.out.print("Enter text: ");
        String query = stringScanner.nextLine();

        contactService.searchContact(query);
    }
}
