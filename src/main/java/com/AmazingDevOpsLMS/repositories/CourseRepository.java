/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.AmazingDevOpsLMS.repositories;

import com.AmazingDevOpsLMS.model.Course;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author samuel
 */
public interface CourseRepository extends PagingAndSortingRepository<Course,String>{
    
}
