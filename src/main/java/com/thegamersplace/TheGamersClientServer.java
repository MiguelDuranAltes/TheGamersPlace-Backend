package com.thegamersplace;

import com.thegamersplace.domain.exception.OperationNotAllowed;
import com.thegamersplace.domain.exception.UserLoginExistsException;
import com.thegamersplace.infrastructure.config.DatabaseLoader;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
public class TheGamersClientServer {
    @Autowired
    @Lazy
    private DatabaseLoader databaseLoader;

    public static void main(String[] args) {
        SpringApplication.run(TheGamersClientServer.class, args);
    }

    @PostConstruct
    public void init() throws InterruptedException {
        try {
            databaseLoader.loadData();
        } catch (UserLoginExistsException | OperationNotAllowed e) {
            // Usuarios ya existen, es normal en reinicios.
        }
    }
}

