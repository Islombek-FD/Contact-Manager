package uz.practice;

import uz.practice.utils.DBUtils;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
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

    public static void showMenu() {
        System.out.println("*** MENU ***");
        System.out.println("0. EXIT");
        System.out.println("1. ADD CONTACT");
        System.out.println("2. CONTACT LIST ");
        System.out.println("3. DELETE CONTACT");
        System.out.println("4. SEARCH CONTACT");
    }

    public static int getAction() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter action: ");
        return scanner.nextInt();
    }

    public static void addContact() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        Contact contact = new Contact();

        contact.setName(name);
        contact.setSurname(surname);
        contact.setPhone(phone);

        ContactService contactService = new ContactService();
        contactService.addContact(contact);
    }

    public static void listContact() {
        ContactService contactService = new ContactService();
        contactService.listContact();
    }

    public static void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        ContactService contactService = new ContactService();
        contactService.deleteContact(phone);
    }

    public static void searchContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text: ");
        String query = scanner.nextLine();

        ContactService contactService = new ContactService();
        contactService.searchContact(query);
    }
}
