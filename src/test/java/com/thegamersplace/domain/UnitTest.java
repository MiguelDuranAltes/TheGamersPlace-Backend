package com.thegamersplace.domain;

import com.thegamersplace.domain.entity.User;
import com.thegamersplace.domain.entity.UserRole;
import com.thegamersplace.domain.exception.NotFoundException;
import com.thegamersplace.domain.exception.OperationNotAllowed;
import com.thegamersplace.domain.exception.UserLoginExistsException;
import com.thegamersplace.domain.service.UserService;
import com.thegamersplace.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Nested
    class user_given_valid_params {
        User user = User.builder().login("testUser").password("Valid@123").role(UserRole.USER).build();

        @BeforeEach
        void setUp() {
            when(userRepository.save(any())).thenReturn(user);
            when(passwordEncoder.encode(any())).thenAnswer(invocation -> invocation.getArgument(0)); // Simula que la contraseña no cambia
        }

        @Test
        void should_create_user() throws OperationNotAllowed, UserLoginExistsException {
            assertTrue(user.validateUser());
            User result = userService.createBasicUser(user.getLogin(), user.getPassword(), user.getRole());

            assertThat(result).isNotNull();
            // lo comento porque no se puede acceder a la propiedad id ya que el mock no asigna un id
            //assertThat(result.getId()).isNotNull();
            assertThat(result.getLogin()).isEqualTo(user.getLogin());
            assertThat(result.getPassword()).isEqualTo(user.getPassword());
        }
    }

    @Nested
    class user_given_invalid_params {

        @Test
        void should_throw_exception_if_password_too_short() {
            User user = User.builder().login("testUser").password("123").role(UserRole.USER).build();
            assertFalse(user.validateUser());
            OperationNotAllowed e = assertThrows(
                    OperationNotAllowed.class, () -> userService.createBasicUser(user.getLogin(), user.getPassword(), user.getRole())
            );

            assertThat(e.getMessage()).contains("Password must be at least 6 characters long");
        }

        @Test
        void should_throw_exception_if_password_lacks_special_character() {
            User user = User.builder().login("testUser").password("Password123").role(UserRole.USER).build();
            assertFalse(user.validateUser());
            OperationNotAllowed e = assertThrows(
                    OperationNotAllowed.class, () -> userService.createBasicUser(user.getLogin(), user.getPassword(), user.getRole())
            );

            assertThat(e.getMessage()).contains("Password must contain at least one of the following characters: @#$%&");
        }
    }

    @Nested
    class given_existing_user {
        User user = User.builder().login("testUser").password("Valid@123").role(UserRole.USER).build();

        @BeforeEach
        void setUp() {
            when(userRepository.findByLogin(user.getLogin())).thenReturn(java.util.Optional.of(user));
        }

        @Test
        void should_throw_exception_if_user_exists() {
            UserLoginExistsException e = assertThrows(
                    UserLoginExistsException.class, () -> userService.createBasicUser(user.getLogin(), user.getPassword(), user.getRole())
            );

            assertThat(e.getMessage()).contains(user.getLogin());
        }
    }

    @Nested
    class update_user {
        User user = User.builder()
                .login("testUser")
                .password("Valid@123")
                .role(UserRole.USER)
                .name("Old Name")
                .city("Old City")
                .blocked(false)
                .build();

        @BeforeEach
        void setUp() {
            when(userRepository.findByLogin(user.getLogin())).thenReturn(Optional.of(user));
        }

        @Test
        void should_update_user() throws NotFoundException {
            // Simulamos que el usuario ya existe en el sistema
            when(userRepository.findByLogin(user.getLogin())).thenReturn(Optional.of(user));

            when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
                User savedUser = invocation.getArgument(0);
                // Devolvemos el usuario actualizado
                user.setName(savedUser.getName());
                user.setCity(savedUser.getCity());
                return user;
            });

            // Creamos un nuevo objeto de usuario con los cambios (nombre y ciudad actualizados)
            User updatedUser = User.builder()
                    .login("testUser")
                    .password("Valid@123")
                    .role(UserRole.USER)
                    .name("Updated User")
                    .city("Updated City")
                    .build();

            // Llamamos al método de actualización
            User result = userService.updateUser(updatedUser);

            // Comprobamos que el resultado no es null
            assertThat(result).isNotNull();

            // Comprobamos que el nombre y la ciudad se hayan actualizado correctamente
            assertThat(result.getName()).isEqualTo("Updated User");
            assertThat(result.getCity()).isEqualTo("Updated City");

            // Verificamos que la actualización en el mock de userRepository fue realizada
            verify(userRepository).save(any(User.class));
        }




        @Test
        void should_throw_exception_if_user_not_found() {
            when(userRepository.findByLogin(user.getLogin())).thenReturn(Optional.empty());
            User updatedUser = User.builder()
                    .login("testUser")
                    .name("Updated Name")
                    .city("Updated City")
                    .build();
            assertThrows(NotFoundException.class, () -> userService.updateUser(updatedUser));
        }

        @Test
        void should_update_blocked() throws NotFoundException {
            // Asegurar que el usuario inicialmente no está bloqueado
            assertFalse(user.getBlocked());

            // Simular el cambio de estado de bloqueado
            when(userRepository.save(any())).thenAnswer(invocation -> {
                User savedUser = invocation.getArgument(0);
                return savedUser;
            });

            Boolean result = userService.updateBlocked(user.getLogin());

            assertThat(result).isTrue();
            assertThat(user.getBlocked()).isTrue();
        }

        @Test
        void should_throw_exception_if_user_not_found_when_updating_blocked() {
            when(userRepository.findByLogin(user.getLogin())).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class, () -> userService.updateBlocked(user.getLogin()));
        }


    }

    @Nested
    class delete_user {
        User user = User.builder().login("testUser").password("Valid@123").role(UserRole.USER).build();

        @BeforeEach
        void setUp() {
        }

        @Test
        void should_delete_user() throws Exception {
            when(userRepository.findByLogin(user.getLogin())).thenReturn(Optional.of(user));
            userService.deleteUser(user.getLogin());
            verify(userRepository, times(1)).delete(user);
        }

        @Test
        void should_throw_exception_if_user_not_found() {
            when(userRepository.findByLogin(user.getLogin())).thenReturn(Optional.empty());
            assertThrows(NotFoundException.class, () -> userService.deleteUser(user.getLogin()));
        }
    }



}
