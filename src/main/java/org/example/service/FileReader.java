package org.example.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("init")
public class FileReader {

    private final InterfaceService interfaceService;
    @Value("${app.default-contacts-path}")
    private String path;


    public FileReader(InterfaceService interfaceService) {
        this.interfaceService = interfaceService;

    }

    @PostConstruct
    public void readFile() {
        interfaceService.readFile(path);
    }


}
