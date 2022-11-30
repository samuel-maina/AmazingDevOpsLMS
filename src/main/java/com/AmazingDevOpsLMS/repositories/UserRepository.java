/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.AmazingDevOpsLMS.repositories;

import com.AmazingDevOpsLMS.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author samuel
 */
public interface UserRepository extends PagingAndSortingRepository<User,String>{

 @Query("select count(u) from User u")
    public int getUserCount();

    @Query("select count(u) from User u where enabled=true")
    public int getActiveUserCount();
    
}
