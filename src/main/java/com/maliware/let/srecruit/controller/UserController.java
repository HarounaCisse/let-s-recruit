package com.maliware.let.srecruit.controller;

import com.maliware.let.srecruit.model.User;
import com.maliware.let.srecruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    public UserService userService;

    @GetMapping
    ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(this.userService.displayUsers());
    }
}
