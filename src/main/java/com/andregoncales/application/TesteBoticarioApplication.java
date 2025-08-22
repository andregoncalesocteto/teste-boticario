package com.andregoncales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TesteBoticarioApplication {
    public static void main(String[] args) {
        System.out.println("*******************************************");
        System.out.println("APPLICATION STARTED");
        System.out.println("*******************************************");
        SpringApplication.run(TesteBoticarioApplication.class, args);
    }
}