/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AmazingDevOpsLMS.services;

import com.AmazingDevOpsLMS.exceptions.ResourceNotFoundException;
import com.AmazingDevOpsLMS.model.Cluster;
import com.AmazingDevOpsLMS.repositories.ClusterRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samuel
 */
@Service
public class ClusterService {
    
    @Autowired
    private ClusterRepository clusterRepository;
    
    public Iterable<Cluster> getClusters() {
        return clusterRepository.findAll();
    }
    
    public Cluster saveCluster(Cluster cluster) {
        return clusterRepository.save(cluster);
    }
    
    public void deleteClusterById(String Id) {
        Cluster cluster = this.findClusterById(Id);
        clusterRepository.delete(cluster);
    }
    
    public void updateClusterById(Cluster cluster, String Id) {
        
        Cluster cluster_ = this.findClusterById(Id);
        cluster_.setCourse(cluster.getCourse());
        cluster_.setStart(cluster.getStart());
        cluster_.setEnd(cluster.getEnd());
        clusterRepository.save(cluster_);
        
    }
    
    public Cluster findClusterById(String Id) {
        Optional<Cluster> cluster = clusterRepository.findById(Id);
        if (cluster.isPresent()) {
            return cluster.get();
        } else {
            throw new ResourceNotFoundException("");
        }
    }
    
}
