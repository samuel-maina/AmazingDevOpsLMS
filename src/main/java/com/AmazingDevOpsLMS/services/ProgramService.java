/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AmazingDevOpsLMS.services;

import com.AmazingDevOpsLMS.exceptions.ResourceNotFoundException;
import com.AmazingDevOpsLMS.model.Program;
import com.AmazingDevOpsLMS.repositories.ProgramRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samuel
 */
@Service
public class ProgramService {
    @Autowired
    private ProgramRepository programRepository;
    
    public Iterable<Program> getPrograms() {
        return programRepository.findAll();
    }
    
    public Program saveProgram(Program program) {
        return programRepository.save(program);
    }
    
    public void deleteProgramById(String Id) {
        Program program = this.findProgramById(Id);
        programRepository.delete(program);
    }
    
    public void updateProgramById(Program program, String Id) {
        
        Program program_ = this.findProgramById(Id);
        program_.setName(program.getName());
        programRepository.save(program_);
        
    }
    
    public Program findProgramById(String Id) {
        Optional<Program> program = programRepository.findById(Id);
        if (program.isPresent()) {
            return program.get();
        } else {
            throw new ResourceNotFoundException("");
        }
    }
    
}
