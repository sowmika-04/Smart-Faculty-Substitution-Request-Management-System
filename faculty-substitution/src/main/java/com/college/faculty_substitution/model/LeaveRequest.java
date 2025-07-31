package com.college.faculty_substitution.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String facultyId;
    private String facultyName;
    private String day;
    private int period;
    private String substituteId;
    private String substituteFaculty;
    private String status;
    private String reason;
    private LocalDateTime leaveTime;
    private boolean accepted;

    // Default constructor
    public LeaveRequest() {
    }

    // Parameterized constructor
    public LeaveRequest(String facultyId, String facultyName, String day, int period,
                        String substituteId, String substituteFaculty, String status,
                        String reason, LocalDateTime leaveTime, boolean accepted) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.day = day;
        this.period = period;
        this.substituteId = substituteId;
        this.substituteFaculty = substituteFaculty;
        this.status = status;
        this.reason = reason;
        this.leaveTime = leaveTime;
        this.accepted = accepted;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
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

    public String getSubstituteId() {
        return substituteId;
    }

    public void setSubstituteId(String substituteId) {
        this.substituteId = substituteId;
    }

    public String getSubstituteFaculty() {
        return substituteFaculty;
    }

    public void setSubstituteFaculty(String substituteFaculty) {
        this.substituteFaculty = substituteFaculty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(LocalDateTime leaveTime) {
        this.leaveTime = leaveTime;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        return "LeaveRequest{" +
                "id=" + id +
                ", facultyId='" + facultyId + '\'' +
                ", facultyName='" + facultyName + '\'' +
                ", day='" + day + '\'' +
                ", period=" + period +
                ", substituteId='" + substituteId + '\'' +
                ", substituteFaculty='" + substituteFaculty + '\'' +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", leaveTime=" + leaveTime +
                ", accepted=" + accepted + '}';
    }
}