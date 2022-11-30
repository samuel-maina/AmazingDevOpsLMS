package com.AmazingDevOpsLMS.services;



import com.AmazingDevOpsLMS.exceptions.ResourceNotFoundException;
import com.AmazingDevOpsLMS.model.User;
import com.AmazingDevOpsLMS.repositories.UserRepository;
import com.AmazingDevOpsLMS.repositories.UserRolesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

/**
 * class handles the auto-activation and auto-deactivation of user accounts
 * based on time set
 *
 * 03-10-2022
 *
 * @author Samuel Maina
 * @version 1.0
 */
@Service
@EnableScheduling
public class UserService {

    @Autowired
    private UserRepository userRepository;


    private UserRolesRepository userRolesRepository;

    /**
     * Saves users
     *
     * @param user User object/instance
     * @return scheduled user
     */
    public User saveUser(User user) {
        
        return userRepository.save(user);
    }

    /**
     * retrieves all users
     *
     * @return list of users
     */
    public Iterable getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * finds user by id
     *
     * @param userId
     * @return user object
     */
    public User findUserById(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResourceNotFoundException("user not found");
        }
    }

    /**
     * sets the activation and deactivation status of an account
     */


    /**
     * schedules an account
     *
     * @param scheduledAccountActivator time object [activation and deactivation
     * time]
     * @param userId unique user identifier
     * @return ScheduledAccountActivator object
     */


    /**
     * update a user object
     *
     * @param user object
     * @param userId unique identifier
     * @return updated user object
     */
    public User update(User user, String userId) {
        User user_ = findUserById(userId);
        user_.setAddress(user.getAddress());
        user_.setEmail(user.getEmail());
        user_.setFirstname(user.getFirstname());
        user_.setLastname(user.getLastname());
        user_.setPhone(user.getPhone());
        return userRepository.save(user_);
    }

    public void activateUser(String id) {
        User user = findUserById(id);
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void deActivateUser(String id) {
        User user = findUserById(id);
        user.setEnabled(false);
        userRepository.save(user);
    }

    public boolean getStatus(String id) {
        return findUserById(id).isEnabled();
    }

    public List<User> findUsersByRole(String roleId) {
        return userRolesRepository.findUsersByRole(roleId);
    }

    public int getUserCount() {
        return userRepository.getUserCount();
    }

 

    public int getActiveUserCount() {
        return userRepository.getActiveUserCount();
    }
}
