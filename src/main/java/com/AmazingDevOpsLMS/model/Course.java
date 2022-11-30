/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AmazingDevOpsLMS.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author samuel
 */
@Entity
public class Course {
    @Id
    private String id;
    private String name;
    private String description;
    @ManyToOne
    private Program program;
    
    @OneToMany(mappedBy="course")
    private List<Cluster> cluster;

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

    public List<Cluster> getCluster() {
        return cluster;
    }

    public void setCluster(List<Cluster> cluster) {
        this.cluster = cluster;
    }



    
    
    
    
    
}
