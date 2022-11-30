/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AmazingDevOpsLMS.services;

import com.AmazingDevOpsLMS.exceptions.ResourceNotFoundException;
import com.AmazingDevOpsLMS.model.Course;
import com.AmazingDevOpsLMS.repositories.CourseRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samuel
 */
@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;
    
    public Iterable<Course> getCourses() {
        return courseRepository.findAll();
    }
    
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }
    
    public void deleteCourseById(String Id) {
        Course course = this.findCourseById(Id);
        courseRepository.delete(course);
    }
    
    public void updateCourseById(Course course, String Id) {
        
        Course course_ = this.findCourseById(Id);
        course_.setDescription(course.getDescription());
        course_.setName(course.getName());
        courseRepository.save(course_);
        
    }
    
    public Course findCourseById(String Id) {
        Optional<Course> course = courseRepository.findById(Id);
        if (course.isPresent()) {
            return course.get();
        } else {
            throw new ResourceNotFoundException("");
        }
    }
    
}
