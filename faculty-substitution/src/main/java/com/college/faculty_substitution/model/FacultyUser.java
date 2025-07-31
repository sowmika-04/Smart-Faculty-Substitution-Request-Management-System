package com.college.faculty_substitution.model;

import jakarta.persistence.*;

@Entity
@Table(name = "faculty_users")
public class FacultyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String facultyId;

    @Column(nullable = false)
    private String name;

    private String email;
    private String subject;

    @Column(nullable = false)
    private String password;

    // Constructors
    public FacultyUser() {}

    public FacultyUser(String facultyId, String name, String email, String subject, String password) {
        this.facultyId = facultyId;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFacultyId() { return facultyId; }
    public void setFacultyId(String facultyId) { this.facultyId = facultyId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
