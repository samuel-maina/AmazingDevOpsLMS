/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.AmazingDevOpsLMS.repositories;

import com.AmazingDevOpsLMS.model.Student;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author samuel
 */
public interface StudentRepository extends CrudRepository<Student,String> {
    
}
