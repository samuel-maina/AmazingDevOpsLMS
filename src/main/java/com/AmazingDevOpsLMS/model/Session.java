package com.AmazingDevOpsLMS.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 *
 * @author samuel
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"course", "sessionStart"})})
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate sessionStart;
    private LocalDate sessionEnd;
    @JsonIgnore
    @JoinColumn(name = "course")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Course course;

    public LocalDate getSessionStart() {
        return sessionStart;
    }

    public void setSessionStart(LocalDate sessionStart) {
        this.sessionStart = sessionStart;
    }

    public LocalDate getSessionEnd() {
        return sessionEnd;
    }

    public void setSessionEnd(LocalDate sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

    public Course getCourse() {
        return course;
    }
    public String getCoursename(){
    return course.getName();
    
    }
    public String getProgramname(){
    return course.getProgram().getName();
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    

}
