package com.college.faculty_substitution.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SubstitutionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requestingFacultyId;
    private String substituteFacultyId;
    private String day;
    private int period;
    private String subject;
    private String className;
    private LocalDateTime timestamp = LocalDateTime.now();

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRequestingFacultyId() { return requestingFacultyId; }
    public void setRequestingFacultyId(String requestingFacultyId) { this.requestingFacultyId = requestingFacultyId; }

    public String getSubstituteFacultyId() { return substituteFacultyId; }
    public void setSubstituteFacultyId(String substituteFacultyId) { this.substituteFacultyId = substituteFacultyId; }

    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }

    public int getPeriod() { return period; }
    public void setPeriod(int period) { this.period = period; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
