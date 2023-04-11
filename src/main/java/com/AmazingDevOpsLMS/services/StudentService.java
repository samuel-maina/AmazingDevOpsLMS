/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AmazingDevOpsLMS.services;

import com.AmazingDevOpsLMS.exceptions.EmailAlreadyRegisteredException;
import com.AmazingDevOpsLMS.exceptions.StudentNotFoundException;
import com.AmazingDevOpsLMS.model.Role;
import com.AmazingDevOpsLMS.model.Roles;
import com.AmazingDevOpsLMS.model.Student;
import com.AmazingDevOpsLMS.model.UserRole;
import com.AmazingDevOpsLMS.model.VerificationToken;
import com.AmazingDevOpsLMS.repositories.RoleRepository;
import com.AmazingDevOpsLMS.repositories.StudentRepository;
import com.AmazingDevOpsLMS.repositories.UserRolesRepository;
import com.AmazingDevOpsLMS.repositories.VerificationTokenRepository;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author samuel
 */
@Service
public class StudentService  {

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;
    
     @Autowired
    private JavaMailSender mailSender;



    public Student findStudentById(String email) {
        return studentRepository.findById(email).orElseThrow(() -> new StudentNotFoundException(""));
    }

    public Student saveStudent(Student student,HttpServletRequest request) {

        Optional<Student> student_ = studentRepository.findById(student.getEmail());
        if (student_.isPresent()) {
            throw new EmailAlreadyRegisteredException("");
        }

        
        String code = RandomString.make(64);
        Student temp = studentRepository.save(student);
        UserRole userRole = new UserRole();
        Role role_ = roleRepository.findById(Roles.STUDENT.ordinal()).get();
        userRole.setUser(temp);
        userRole.setRoles(role_);
        userRolesRepository.save(userRole);
        this.createVerificationToken(student, code);
        String appUrl = request.getContextPath();
        try {
            sendVerificationEmail(student,code, appUrl);
        } catch (MessagingException ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

    
    public void createVerificationToken(Student student, String token) {
        VerificationToken myToken = new VerificationToken(token, student);
        tokenRepository.save(myToken);
    }

    
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }
    private void sendVerificationEmail(Student student,String token, String siteURL)
        throws MessagingException, UnsupportedEncodingException {
    String toAddress = student.getEmail();
    String fromAddress = "test@amazingdevops.com";
    String senderName = "AmazingDevops";
    String subject = "Please verify your registration";
    String content = "Dear [[name]],<br>"
            + "Please click the link below to verify your registration:<br>"
            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
            + "Thank you,<br>"
            + "Your company name.";
     
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);
     
    helper.setFrom(fromAddress, senderName);
    helper.setTo(toAddress);
    helper.setSubject(subject);
     
    content = content.replace("[[name]]", student.getFirstname());
    String verifyURL = siteURL + "/verify?code=" + token;
     
    content = content.replace("[[URL]]", verifyURL);
     
    helper.setText(content, true);
     
    mailSender.send(message);
     
}
}
