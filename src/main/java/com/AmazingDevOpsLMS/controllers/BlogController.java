/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AmazingDevOpsLMS.controllers;

import com.AmazingDevOpsLMS.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author samuel
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/blog/")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("{blogId}")
    public ResponseEntity<?> findBlogById(@PathVariable String blogId) {
        return new ResponseEntity<>(blogService.findBlogById(blogId),HttpStatus.OK);
    }

}
