package com.notebank.controllers;

import com.notebank.models.dto.LoginDto;
import com.notebank.models.dto.UserDto;
import com.notebank.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(method = RequestMethod.POST, value = "register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        return usersService.register(userDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        return usersService.login(loginDto);
    }

}
