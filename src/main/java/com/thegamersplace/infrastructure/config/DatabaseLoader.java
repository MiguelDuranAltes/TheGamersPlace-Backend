package com.thegamersplace.infrastructure.config;

import com.thegamersplace.domain.entity.UserRole;
import com.thegamersplace.domain.exception.OperationNotAllowed;
import com.thegamersplace.domain.exception.UserLoginExistsException;
import com.thegamersplace.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseLoader {

    @Autowired
    private UserService userService;

    public void loadData() throws UserLoginExistsException, InterruptedException, OperationNotAllowed {

        userService.createUser("admin", "admin@123", null, "undefined","undefined", UserRole.ADMIN);
        userService.createUser("user", "user@123", null, "undefined","undefined", UserRole.USER);

    }
}
