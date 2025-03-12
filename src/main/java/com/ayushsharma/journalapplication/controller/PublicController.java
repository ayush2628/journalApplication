package com.ayushsharma.journalapplication.controller;

import com.ayushsharma.journalapplication.entity.User;
import com.ayushsharma.journalapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @PostMapping("/create-user")
    public void createUserEntry(@RequestBody User user) {
        userService.saveNewUser(user);
    }
}
