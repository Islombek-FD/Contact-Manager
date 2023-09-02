package uz.practice;

import uz.practice.controller.ContactController;

public class Main {

    public static void main(String[] args) {
        ContactController contactController = new ContactController();
        contactController.start();
    }
}
