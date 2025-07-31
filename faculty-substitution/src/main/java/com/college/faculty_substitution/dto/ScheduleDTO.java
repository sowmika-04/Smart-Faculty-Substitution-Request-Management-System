package com.college.faculty_substitution.dto;

public class ScheduleDTO {
    private String day;
    private String period;
    private String subject;
    private String className;

    public ScheduleDTO(String day, String period, String subject, String className) {
        this.day = day;
        this.period = period;
        this.subject = subject;
        this.className = className;
    }

    // Getters and Setters
    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }

    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
}
