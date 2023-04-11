/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AmazingDevOpsLMS.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author samuel
 */
@Entity
public class Student extends User {

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<CourseRegistration> courseRegistration;
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Payments> payments;
    
     @OneToOne
    private VerificationToken token;

    public VerificationToken getToken() {
        return token;
    }

    public void setToken(VerificationToken token) {
        this.token = token;
    }

    public Set<CourseRegistration> getCourseRegistration() {
        return courseRegistration;
    }

    public void setCourseRegistration(Set<CourseRegistration> courseRegistration) {
        this.courseRegistration = courseRegistration;
    }

    public Set<Payments> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payments> payments) {
        this.payments = payments;
    }

}
