/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AmazingDevOpsLMS.controllers;

import com.AmazingDevOpsLMS.model.Roles;
import com.AmazingDevOpsLMS.model.Student;
import com.AmazingDevOpsLMS.model.User;
import com.AmazingDevOpsLMS.model.VerificationToken;
import com.AmazingDevOpsLMS.services.StudentService;
import com.AmazingDevOpsLMS.services.UserService;
import java.util.Calendar;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author samuel
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/users/")
public class UserController {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private MessageSource messages;

    @GetMapping
    public ResponseEntity<?> viewUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/students/signup")
    public ResponseEntity<?> saveStudent(@RequestBody Student student,HttpServletRequest request) {
        String password = student.getPassword();
        student.setPassword(encoder.encode(password));
        student.setEnabled(false);
        return new ResponseEntity<>(studentService.saveStudent(student,request), HttpStatus.OK);
    }

    @GetMapping("/registrationConfirm")
    public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {
/*
        Locale locale = request.getLocale();

        VerificationToken verificationToken = service.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        Student student = verificationToken.getStudent();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", messageValue);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        student.setEnabled(true);
        userService.saveUser(student, Roles.STUDENT);
*/
        return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
    }

    @PostMapping("/instructor/signup")
    public ResponseEntity<?> saveInstructor(@RequestBody User user) {
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        user.setEnabled(false);
        return new ResponseEntity<>(userService.saveUser(user, Roles.INSTRUCTOR), HttpStatus.OK);
    }

    @PostMapping("/administrator/signup")
    public ResponseEntity<?> saveAdministrator(@RequestBody User user) {
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        user.setEnabled(false);
        return new ResponseEntity<>(userService.saveUser(user, Roles.ADMINISTRATOR), HttpStatus.OK);
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activate(@RequestBody User user) {
        userService.activateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
