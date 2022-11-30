/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AmazingDevOpsLMS.controllers;

import com.AmazingDevOpsLMS.model.User;
import com.AmazingDevOpsLMS.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author samuel
 */
@RestController
@RequestMapping("/api/v1/users/")
public class UserController {
    
       @Autowired
    private PasswordEncoder encoder; 
    
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<?> viewUsers(){
    return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
    @PostMapping("signup")
    public ResponseEntity<?> save(@RequestBody User user){
                String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        user.setEnabled(false);
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.OK);
    }
    
}
