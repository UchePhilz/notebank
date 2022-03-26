/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notebank.services;

import com.google.gson.Gson;
import com.notebank.models.Users;
import com.notebank.models.dto.LoginDto;
import com.notebank.utils.TokenGenerator;
import com.notebank.models.dto.UserDto;
import com.notebank.repositories.UsersRepository;
import com.notebank.utils.PasswordEncoder;
import com.notebank.utils.ResponseObject;
import com.notebank.utils.ValidatorMessage;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Uche Powers
 */
@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public UsersService() {
    }

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public ResponseEntity<?> register(UserDto userDto) {

        ResponseEntity<?> httpResponse = null;

        List<ValidatorMessage> validate = ValidatorMessage.validate(userDto);

        if (validate.isEmpty()) {
            Optional<Users> findByEmail = usersRepository.findByEmail(userDto.getEmail());

            if (!findByEmail.isPresent()) {

                Users users = userDto.returnUser();
                users.setPassword(PasswordEncoder.encoder(userDto.getPassword()));
                users.setAccessToken(TokenGenerator.generalToken());

                usersRepository.save(users);
                userDto.setPassword(null);
                httpResponse = new ResponseObject(HttpStatus.OK, userDto).HttpResponse();

            } else {
                httpResponse = new ResponseObject(HttpStatus.ALREADY_REPORTED,
                        new ValidatorMessage("email", "already exist", userDto.getEmail())).HttpResponse();
            }
        } else {
            httpResponse = new ResponseObject(HttpStatus.BAD_REQUEST, validate).HttpResponse();
        }
        System.out.println("Output: " + httpResponse.getStatusCode().name());
        return httpResponse;
    }

    public ResponseEntity<?> login(LoginDto loginDto) {

        ResponseEntity<?> httpResponse = null;

        List<ValidatorMessage> validate = ValidatorMessage.validate(loginDto);

        if (validate.isEmpty()) {
            Optional<Users> findByEmail = usersRepository.findByEmail(loginDto.getEmail());

            if (findByEmail.isPresent()) {
                Users user = findByEmail.get();
                boolean checkPassword = PasswordEncoder.checkPassword(loginDto.getPassword(), findByEmail.get().getPassword());

                if (checkPassword) {

                    user.setAccessToken(TokenGenerator.generalToken());
                    user.setTs(new Date());
                    usersRepository.save(user);
                    user.setPassword(null);
                    httpResponse = new ResponseObject(HttpStatus.OK, user).HttpResponse();
                } else {
                    httpResponse = new ResponseObject(HttpStatus.UNAUTHORIZED,
                            new ValidatorMessage("email", "Unknown user", loginDto.getEmail())).HttpResponse();
                }

            } else {
                httpResponse = new ResponseObject(HttpStatus.UNAUTHORIZED,
                        new ValidatorMessage("email", "Unknown user", loginDto.getEmail())).HttpResponse();
            }

        } else {
            httpResponse = new ResponseObject(HttpStatus.BAD_REQUEST, validate).HttpResponse();
        }
        return httpResponse;
    }

}
