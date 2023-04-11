/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AmazingDevOpsLMS.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author samuel
 */
@Entity
public class Course {

    @Id
    private String id;
    private String name;
    @Column(length = 600)
    private String description;
    
    private int cost;
    @JsonIgnore
    @ManyToOne
    private Program program;

    @Transient
    private boolean registered = false;
    
     @OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<CourseRegistration> courseRegistration;

    
    
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Session> sessions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

  
    

    public Set<CourseRegistration> getCourseRegistration() {
        return courseRegistration;
    }

    public void setCourseRegistration(Set<CourseRegistration> courseRegistration) {
        this.courseRegistration = courseRegistration;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }
    
    

}
