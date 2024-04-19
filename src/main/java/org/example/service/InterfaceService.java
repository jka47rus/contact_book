package org.example.service;


import org.example.model.PersonInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class InterfaceService {

    private final Map<String, PersonInfo> people = new HashMap<>();
    @Value("${app.file-for-save}")
    private String pathForSave;

    public String delete(String email) {
        String message = "";
        if (!people.containsKey(email.trim())) {
            message = MessageFormat.format("Пользователь с email {0} не найден!", email);
        } else {
            people.remove(email.trim());
            message = "Контакт успешно удален!" + "\n";
        }
        return message;
    }

    public String addContact(String info) {
        String message = "";
        PersonInfo personInfo = parse(info);
        if (personInfo == null) {
            return message = "Ошибка при попытке создания пользователя!" + "\n";
        }
        for (Map.Entry<String, PersonInfo> entry : people.entrySet()) {
            if (entry.getKey().equals(personInfo.getEmail())) {
                message = "Пользователь с email " + personInfo.getEmail() + " уже существует!" + "\n";
            }
        }

        if (message.equals("")) {
            people.put(personInfo.getEmail(), personInfo);
            message = "Контакт успешно добавлен!" + "\n";
        }

        return message;
    }

    private PersonInfo parse(String info) {
        PersonInfo personInfo = new PersonInfo();
        String[] values = info.split(";");
        if (values.length != 3) {
            personInfo = null;
        } else {
            personInfo.setName(values[0].trim());
            personInfo.setPhoneNumber(values[1].trim());
            personInfo.setEmail(values[2].trim());
        }
        return personInfo;
    }

    public String saveToFile() {
        String message = "";
        List<String> contacts = new ArrayList<>();
        people.entrySet().forEach(entry -> {
            StringBuilder builder = new StringBuilder();
            builder.append(entry.getValue().getName()).append(";").append(entry.getValue().getPhoneNumber())
                    .append(";").append(entry.getValue().getEmail());
            contacts.add(builder.toString());
        });
        if (contacts.isEmpty()) {
            message = "Ошибка при попытке записи данных в файл!" + "\n";
        } else {
            try {
                Files.write(Paths.get(pathForSave), contacts);
                message = "Файл успешно записан!" + "\n";
            } catch (Exception ex) {
                System.out.println(ex);
                message = "Ошибка при попытке записи данных в файл!" + "\n";
            }
        }

        return message;
    }


    public void readFile(String path) {
        String message = "";

        try {
            List<String> lines = Files.readAllLines(Paths.get(path)); // "resources/default-contacts.txt"
            lines.forEach(line -> {
                PersonInfo personInfo = new PersonInfo();
                personInfo = parse(line);
                people.put(personInfo.getEmail(), personInfo);
            });

        } catch (Exception ex) {
            System.out.println(ex);
            message = "Ошибка при чтении файла!";
        }
    }


    public Map<String, PersonInfo> getPeople() {
        return people;
    }
}
