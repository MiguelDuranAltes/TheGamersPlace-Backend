package com.thegamersplace.infrastructure.integrationTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "com.thegamersplace",
        })
@EnableMongoRepositories(
        basePackages = {"com.thegamersplace.infrastructure"})
public class TestApplication {

    public static void main(final String[] args) {
        System.setProperty("user.timezone", "UTC");
        SpringApplication.run(TestApplication.class, args);
    }

}