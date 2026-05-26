package com.thegamersplace.infrastructure.web.controller;

import com.thegamersplace.domain.entity.UserRole;
import com.thegamersplace.domain.exception.OperationNotAllowed;
import com.thegamersplace.domain.exception.UserLoginExistsException;
import com.thegamersplace.domain.service.UserService;
import com.thegamersplace.infrastructure.web.controller.dto.LoginDTO;
import com.thegamersplace.infrastructure.web.controller.dto.UserDTOPrivate;
import com.thegamersplace.infrastructure.web.controller.dto.UserDTORegister;
import com.thegamersplace.infrastructure.web.exceptions.CredentialsAreNotValidException;
import com.thegamersplace.infrastructure.web.exceptions.RequestBodyNotValidException;
import com.thegamersplace.infrastructure.security.JWTToken;
import com.thegamersplace.infrastructure.security.TokenProvider;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    @Autowired
    public AuthController(TokenProvider tokenProvider, AuthenticationManager authenticationManager, UserService userService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public JWTToken authenticate(@Valid @RequestBody LoginDTO loginDTO) throws CredentialsAreNotValidException {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getLogin(), loginDTO.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication);
            return new JWTToken(jwt);
        } catch (AuthenticationException e) {
            logger.warn(e.getMessage(), e);
            throw new CredentialsAreNotValidException(e.getMessage());
        }
    }

    @GetMapping("/account")
    public UserDTOPrivate getAccount() {
        return userService.getCurrentUserWithAuthority();
    }

    @PostMapping("/register")
    public void registerAccount(@Valid @RequestBody UserDTORegister account, Errors errors)
            throws UserLoginExistsException, RequestBodyNotValidException, OperationNotAllowed {
        if (errors.hasErrors()) {
            throw new RequestBodyNotValidException(errors);
        }

        userService.createUser(account.getLogin(), account.getPassword(), account.getImageName(),account.getName(),
                account.getCity(), UserRole.USER);
    }
}
