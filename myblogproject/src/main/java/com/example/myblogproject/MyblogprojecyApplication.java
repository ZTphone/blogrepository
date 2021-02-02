package com.example.myblogproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class MyblogprojecyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyblogprojecyApplication.class, args);
    }

}
