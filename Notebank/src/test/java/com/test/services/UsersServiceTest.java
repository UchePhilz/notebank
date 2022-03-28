/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.services;

import com.notebank.models.Users;
import com.notebank.models.dto.LoginDto;
import com.notebank.models.dto.UserDto;
import com.notebank.repositories.UsersRepository;
import com.notebank.services.UsersService;
import com.notebank.utils.PasswordEncoder;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Uche Powers
 */
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsersServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @Autowired
    private UsersService usersService;

    @BeforeEach
    void setUp() {
        this.usersService = new UsersService(this.usersRepository);
    }

    @Order(1)
    @Test
    @DisplayName("Test User Registration")
    public void testRegisterUser() {

        UserDto users = new UserDto("Uche Powers", "uchephilz@gmail.com", "coolandthegang");
        ResponseEntity<?> register1 = usersService.register(users);

        System.out.println("Output 1: " + register1.getStatusCode().name());

        assertThat(register1.getStatusCode().name()).isEqualTo(HttpStatus.OK.name());

    }

    @Order(2)
    @Test
    @DisplayName("Test User Duplicate Registration")
    public void testRegisterDuplicateUser() {

        //create mocks for data neeeded to to test method
        Optional<Users> users = Optional.of(new Users("Uche Powers", "uchephilz@gmail.com", PasswordEncoder.encoder("coolandthegang")));
        Mockito.when(usersRepository.findByEmail(users.get().getEmail())).thenReturn(users);

        UserDto userDto = new UserDto("Uche Powers", "uchephilz@gmail.com", "coolandthegang");
        ResponseEntity<?> register1 = usersService.register(userDto);
        System.out.println("Output 1: " + register1.getStatusCode().name());

        assertThat(register1.getStatusCode().name()).isEqualTo(HttpStatus.ALREADY_REPORTED.name());

    }

    @Order(3)
    @Test
    @DisplayName("Test Successful User Login")
    public void login() {

        Optional<Users> users = Optional.of(new Users("Uche Powers", "uchephilz@gmail.com", PasswordEncoder.encoder("coolandthegang")));
        Mockito.when(usersRepository.findByEmail(users.get().getEmail())).thenReturn(users);

        LoginDto loginDto = new LoginDto("uchephilz@gmail.com", "coolandthegang");

        ResponseEntity<?> login = usersService.login(loginDto);

        assertThat(login.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void validateUser() {

        String accessToken = "fffff";

        Users user = new Users("Uche Powers", "uchephilz@gmail.com", PasswordEncoder.encoder("coolandthegang"));
        user.setId(1);
        user.setAccessToken(accessToken);
        Optional<Users> users = Optional.of(user);

        Mockito.lenient().when(usersRepository.findByAccessToken(accessToken)).thenReturn(users);

        Integer validateUser = usersService.validateUser("fffffOOo");

        assertThat(validateUser==null?0:validateUser).isEqualTo(0);

    }

}
