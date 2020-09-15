package com.project.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMain implements CommandLineRunner {

    @Autowired
    private ExampleClient exampleClient;

    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(SpringBootMain.class);
        sa.run(args);
    }

    @Override
    public void run(String... args) {
        exampleClient.run();
    }
}

