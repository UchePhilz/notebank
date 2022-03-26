/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.util;

import com.notebank.utils.PasswordEncoder;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Uche Powers
 */
public class PasswordEncodertTest {

    @Test
    public void checkPasswordTest() {
        String tryPassword = "testpassword";
        System.out.println("Password: " + tryPassword);

        String encryptedPassword = PasswordEncoder.encoder(tryPassword);
        System.out.println("Encrypted Password: " + encryptedPassword);

        boolean checkPassword = PasswordEncoder.checkPassword(tryPassword, encryptedPassword);
        AssertionsForClassTypes.assertThat(checkPassword).isEqualTo(true);
        
    }

}
