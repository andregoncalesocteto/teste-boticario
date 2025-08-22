package com.andregoncales;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JsonLoaderRunner implements CommandLineRunner {

    private final JsonReaderService jsonReaderService;

    public JsonLoaderRunner(JsonReaderService jsonReaderService) {
        this.jsonReaderService = jsonReaderService;
    }

    @Override
    public void run(String... args) {
        System.out.println("*******************************************");
        System.out.println("JsonLoaderRunner run");
        System.out.println("*******************************************");
        jsonReaderService.loadAllJsonFiles();
    }
}