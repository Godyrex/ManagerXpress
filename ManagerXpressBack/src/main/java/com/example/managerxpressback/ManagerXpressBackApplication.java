package com.example.managerxpressback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ManagerXpressBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerXpressBackApplication.class, args);
    }

}
