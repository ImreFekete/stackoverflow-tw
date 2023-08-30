package com.codecool.stackoverflowtw.controller;


import com.codecool.stackoverflowtw.controller.dto.NewUserDTO;
import com.codecool.stackoverflowtw.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add-new")
    public int addNewUser(@RequestBody NewUserDTO user) {
        return userService.addNewUser(user);
    }

    @PostMapping("/")
    public ResponseEntity<Object> authUser(@RequestBody NewUserDTO user) {
        if (userService.authUser(user) > 0) {
            Map<String, String> data = new HashMap<>();
            data.put("success", "true");
            data.put("userID", String.valueOf(userService.authUser(user)));
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            Map<String, String> data = new HashMap<>();
            data.put("success", "false");
            return new ResponseEntity<>(data, HttpStatus.UNAUTHORIZED);
        }
    }
}
