package com.college.faculty_substitution.dto;

import java.util.List;
import java.util.Map;

public class TimetableRequestDTO {

    private String facultyId;
    private String facultyName;
    private String facultyEmail;

    // Example structure:
    // {
    //   "Monday": [ ["DSA", "CSE-A"], ["", ""], ... ],
    //   "Tuesday": ...
    // }
    private Map<String, List<List<String>>> timetable;

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

    public String getFacultyEmail() {
        return facultyEmail;
    }

    public void setFacultyEmail(String facultyEmail) {
        this.facultyEmail = facultyEmail;
    }

    public Map<String, List<List<String>>> getTimetable() {
        return timetable;
    }

    public void setTimetable(Map<String, List<List<String>>> timetable) {
        this.timetable = timetable;}
}