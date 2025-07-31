package com.college.faculty_substitution.model;

import jakarta.persistence.*;

@Entity
public class TimetableEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String day;
    private int period;
    private String subject;
    private String className;

    @ManyToOne
    @JoinColumn(name = "faculty_user_id")
    private FacultyUser facultyUser;

    public TimetableEntry() {}

    public TimetableEntry(String day, int period, String subject, String className, FacultyUser facultyUser) {
        this.day = day;
        this.period = period;
        this.subject = subject;
        this.className = className;
        this.facultyUser = facultyUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public FacultyUser getFacultyUser() {
        return facultyUser;
    }

    public void setFacultyUser(FacultyUser facultyUser) {
        this.facultyUser = facultyUser;
    }
}