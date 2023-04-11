/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AmazingDevOpsLMS.services;

import com.AmazingDevOpsLMS.exceptions.ResourceNotFoundException;
import com.AmazingDevOpsLMS.model.Course;
import com.AmazingDevOpsLMS.model.Program;
import com.AmazingDevOpsLMS.model.CourseRegistration;
import com.AmazingDevOpsLMS.model.Role;
import com.AmazingDevOpsLMS.model.Roles;
import com.AmazingDevOpsLMS.repositories.CourseRepository;
import com.AmazingDevOpsLMS.repositories.ProgramRepository;
import com.AmazingDevOpsLMS.repositories.RoleRepository;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;

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

    public Iterable<Course> batchInsert() {
        Role role = new Role();
        role.setId(Roles.STUDENT.ordinal());
        role.setRoleType(Roles.STUDENT);
        role.setDescription("student");

        Role role2 = new Role();
        role2.setId(Roles.ADMINISTRATOR.ordinal());
        role2.setRoleType(Roles.ADMINISTRATOR);
        role2.setDescription("administration");

        Role role3 = new Role();
        role3.setId(Roles.INSTRUCTOR.ordinal());
        role3.setRoleType(Roles.STUDENT);
        role3.setDescription("student");

        roleRepository.save(role);
        roleRepository.save(role2);
        roleRepository.save(role3);
        Program program = new Program();
        program.setId("devops");
        
        
        Course course = new Course();
        course.setId("CMIT-202");
        course.setDescription("The goal is to evaluate, install, configure, maintain, and troubleshoot computer hardware components and operating systems. Course Introduction This course is a thorough review of computer hardware and software with emphasis on troubleshooting and application of current and appropriate computing safety and environmental practices.");
        course.setName("Fundamentals of Computer Troubleshooting");
        course.setProgram(program);

        Course course1 = new Course();
        course1.setId("CMIT-265");
        course1.setDescription("Understanding networking is also key in any IT administration role. Learning Outcomes At the conclusion of the course, you should be able to Describe the media, data link layers & physical networks Explain Network Layer Concepts and the use of IP Addressing/Subnetting Explain how IP routing, TCP/IP and UDP work");
        course1.setName("Fundamentals of Networking");
        course1.setProgram(program);

        Course course2 = new Course();
        course2.setId("CMIT-291");
        course2.setDescription("Introduction to Linux Course Outline Introducing Linux The UNIX heritage Linux inception Linux kernel and GNU tools Accessing the System Managing Files and Directories Controlling Access to Linux Resources Leveraging Bash Shell Features Automating Tasks with Shell Scripts Executing Jobs and Processes");
        course2.setName("Introduction to Linux");
        course2.setProgram(program);

        Course course3 = new Course();
        course3.setId("CMIT-320");
        course3.setDescription("Application security measures. Identify operating system holes. The important interplay of privacy and digital rights management. Trends in malware, privacy and security for mobile devices. Ways to prevent network attacks and gaps in security policy. Internet Infrastructure and Protocols. Routing Security.");
        course3.setName("Newtork Security");
        course3.setProgram(program);

        Course course4 = new Course();
        course4.setId("CMIT-321");
        course4.setDescription("Ethical Hacking training teaches techniques that use cyber security knowledge to prevent malicious attacks and intrusions. An Ethical Hacking course can help you build your IT security expertise, opening the door to many jobs in the field and helping you increase your value in any IT position.");
        course4.setName("Ethical Hacking");
        course4.setProgram(program);

        Course course5 = new Course();
        course5.setId("CMIT-326");
        course5.setDescription("The Cloud Technology course is intended to level set learners on key products and technologies - such as compute, storage and networking that make up the \"Cloud\". In addition, learners will learn basic concepts of other technologies like containers, media and telemetry.");
        course5.setName("Cloud Technologies");
        course5.setProgram(program);

        Course course6 = new Course();
        course6.setId("CMIT-202");
        course6.setDescription("Switching, Routing, and Wireless Essentials Companion Guide (CCNAv7) is the official supplemental textbook for the Switching, Routing, and Wireless Essentials course in the Cisco Networking Academy CCNA curriculum. This course describes the architecture, components, and operations of routers and switches in a small network.");
        course6.setName("Switching, Routing and Wireless Essentials");
        course6.setProgram(program);

        Course course7 = new Course();
        course7.setId("CMIT-495");
        course7.setDescription("Our professional CMIT 495 Current Trends and Projects in Computer Networks and Cyber Security Homework Help tutors have been in the industry for many years and know what precisely your professors/lecturers expect from your assignment paper and always write the paper accordingly. This will surely help you in scoring well in your academics.");
        course7.setName("Current trends and projects in computer network and security");
        course7.setProgram(program);

        List<Course> courses = new ArrayList();
        courses.add(course);
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);
        courses.add(course5);
        courses.add(course6);
        courses.add(course7);
        return courseRepository.saveAll(courses);
    }

    public List<Course> findStudentCoursesByProgramId(String id, Principal principal) {
        List<Course> temp = courseRepository.findCoursesByProgramId(id);
        String userEmail = principal.getName();
        for (Course c : temp) {
            List<CourseRegistration> courseRegistration = new ArrayList<>(c.getCourseRegistration());
            for (CourseRegistration cr : courseRegistration) {
                if (cr.getStudent().getEmail().equalsIgnoreCase(userEmail) && cr.getCourse().getId().equals(c.getId())) {
                    c.setRegistered(true);
                }
            }
        }
        return temp;
    }
    
        public List<Course> findCoursesByProgramId(String id) {
        List<Course> temp = courseRepository.findCoursesByProgramId(id);
        
        return temp;
    }

}
