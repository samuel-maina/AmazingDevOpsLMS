package com.AmazingDevOpsLMS.repositories;

import com.AmazingDevOpsLMS.model.Course;
import com.AmazingDevOpsLMS.model.Session;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author samuel
 */
public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query("Select distinct(s.course) from Session s where s.course.program.Id=?1")
    public List<Course> getCourseSessionByProgramId(String Id);

    @Query("Select distinct(s.course) from Session s")
    public List<Course> getCourseSessions();

    @Query("select s from Session s where s.course.id=?1")
    public List<Session> getCourseSessionsByCourseID(String courseID);

}
