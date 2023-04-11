/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AmazingDevOpsLMS.controllers;

import com.AmazingDevOpsLMS.services.SessionService;
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
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/sessions/")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @GetMapping("program/{programId}")
    public ResponseEntity<?> getCourseSessionByProgramId(@PathVariable String programId) {
        return new ResponseEntity<>(sessionService.getCourseSessionByProgramId(programId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllCourseSessions() {
        return new ResponseEntity<>(sessionService.getCourseSessions(), HttpStatus.OK);
    }

    @GetMapping("course/{courseID}")
    public ResponseEntity<?> getCourseSessionByCourseID(@PathVariable String courseID) {
        return new ResponseEntity<>(sessionService.getCourseSessionsByCourseID(courseID), HttpStatus.OK);
    }

}
