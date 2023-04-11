package com.AmazingDevOpsLMS.services;

import com.AmazingDevOpsLMS.model.Course;
import com.AmazingDevOpsLMS.model.Session;
import com.AmazingDevOpsLMS.repositories.SessionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samuel
 */
@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public List<Course> getCourseSessionByProgramId(String Id) {
        return sessionRepository.getCourseSessionByProgramId(Id);
    }
    
    public List<Course> getCourseSessions() {
        return sessionRepository.getCourseSessions();
    }
    public List<Session> getAllCourseSessions() {
        return sessionRepository.findAll();
    }
    
   public List<Session>  getCourseSessionsByCourseID(String courseID){
       return sessionRepository.getCourseSessionsByCourseID(courseID);
   }
}
