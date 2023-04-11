/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AmazingDevOpsLMS.services;


import com.AmazingDevOpsLMS.model.Course;
import com.AmazingDevOpsLMS.model.CourseRegistration;
import com.AmazingDevOpsLMS.model.Student;
import com.AmazingDevOpsLMS.model.User;
import com.AmazingDevOpsLMS.repositories.CourseRegistrationRepository;
import java.security.Principal;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samuel
 */
@Service
public class CourseRegistrationService {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;
    @Autowired
    private CourseService courseService;

    public CourseRegistration registerUserCourses(Principal principal, String courseId) {
        String username = principal.getName();
        User user = userService.findUserById(username);
        Course course = courseService.findCourseById(courseId);
        CourseRegistration courseRegistration = new CourseRegistration();
        courseRegistration.setCourse(course);
        courseRegistration.setStudent((Student) user);
        return courseRegistrationRepository.save(courseRegistration);
    }

    @Transactional
    public void dropStudentCourse(Principal principal, String courseId) {
        String email = principal.getName();
        courseRegistrationRepository.dropStudentCourse(email, courseId);
    }
}
