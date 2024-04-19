package org.example;

import org.example.model.PersonInfo;
import org.example.service.InterfaceService;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

@Component
public class Interface {
    private final Scanner scanner;

    private final InterfaceService interfaceService;

    public Interface(InterfaceService interfaceService) {
        this.interfaceService = interfaceService;
        this.scanner = new Scanner(System.in);
    }


    public void start() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Выберите действие:");
            System.out.println("1. Просмотреть все контакты");
            System.out.println("2. Добавить контакт");
            System.out.println("3. Удалить контакт");
            System.out.println("4. Выгрузить контакты в файл");
            System.out.println("0. Выход");

            String cod = scanner.nextLine();
            Integer choice = Integer.parseInt(cod.trim());

            if (choice == 1) {
                getAllContacts();
                continue;
            }
            if (choice == 2) {
                addContact();
                continue;
            }
            if (choice == 3) {
                deleteContact();
                continue;
            }
            if (choice == 4) {
                putAllContactsToFile();
                continue;
            }
            if (choice == 0) {
                System.out.println("Выход из программы...");
                isRunning = false;
            }

        }

    }

    private void getAllContacts() {
        Map<String, PersonInfo> allContacts = interfaceService.getPeople();
        if (allContacts.isEmpty()) {
            System.out.println("Контакты отсутствуют!" + "\n");
        } else {
            System.out.println("Список контактов");
            allContacts.entrySet().forEach(entry -> {
                System.out.println(entry.getValue().getName() +
                        " | " + entry.getValue().getPhoneNumber() +
                        " | " + entry.getValue().getEmail());
            });
            System.out.println();
        }

    }

    private void addContact() {
        System.out.println("Введите данные контакта в формате: ФИО;телефон;email");
        String info = scanner.nextLine();
        System.out.println(interfaceService.addContact(info));

    }

    private void deleteContact() {
        System.out.println("Введите email контакта для удаления");
        String email = scanner.nextLine();
        System.out.println(interfaceService.delete(email));
    }

    private void putAllContactsToFile() {
        System.out.println(interfaceService.saveToFile());

    }


}
