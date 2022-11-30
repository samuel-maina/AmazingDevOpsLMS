/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AmazingDevOpsLMS.controllers;

import com.AmazingDevOpsLMS.model.Program;
import com.AmazingDevOpsLMS.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author samuel
 */
@RestController
@RequestMapping("/api/v1/programs/")
public class ProgramController {
    @Autowired
    private ProgramService programService;
    
    @GetMapping
    public ResponseEntity<?> getPrograms() {
        return new ResponseEntity<>(programService.getPrograms(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveProgram(@RequestBody Program program) {
        return new ResponseEntity<>(programService.saveProgram(program), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProgramById(@PathVariable String id) {
        programService.deleteProgramById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findProgramById(@PathVariable String id) {
        return new ResponseEntity<>(programService.findProgramById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> UpdateProgramById(@RequestBody Program program, @PathVariable String id) {
        programService.updateProgramById(program, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
   
}
