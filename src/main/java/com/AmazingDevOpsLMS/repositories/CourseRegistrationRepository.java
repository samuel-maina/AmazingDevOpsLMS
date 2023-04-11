/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.AmazingDevOpsLMS.repositories;

import com.AmazingDevOpsLMS.model.CourseRegistration;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author samuel
 */
public interface CourseRegistrationRepository extends CrudRepository<CourseRegistration, Long> {

    @Modifying
    @Query("delete from CourseRegistration c where c.student.email=?1 and c.course.id=?2")
    public void dropStudentCourse(String email, String courseId);

}
