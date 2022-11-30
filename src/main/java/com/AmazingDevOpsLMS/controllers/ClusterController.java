/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AmazingDevOpsLMS.controllers;

import com.AmazingDevOpsLMS.model.Cluster;
import com.AmazingDevOpsLMS.services.ClusterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author samuel
 */
@RestController
@RequestMapping("/api/v1/clusters/")
public class ClusterController {

    @Autowired
    private ClusterService clusterService;

    @GetMapping
    public ResponseEntity<?> getClusters() {
        return new ResponseEntity<>(clusterService.getClusters(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveCluster(@RequestBody Cluster cluster) {
        return new ResponseEntity<>(clusterService.saveCluster(cluster), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteClusterById(@PathVariable String id) {
        clusterService.deleteClusterById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findClusterById(@PathVariable String id) {
        return new ResponseEntity<>(clusterService.findClusterById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> UpdateClusterById(@RequestBody Cluster cluster, @PathVariable String id) {
        clusterService.updateClusterById(cluster, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
