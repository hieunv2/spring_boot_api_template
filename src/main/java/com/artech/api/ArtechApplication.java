package com.artech.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ArtechApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtechApplication.class, args);
    }

}
