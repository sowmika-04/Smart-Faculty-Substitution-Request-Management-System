package com.college.faculty_substitution.service;

import com.college.faculty_substitution.model.TimetableEntry;
import com.college.faculty_substitution.repository.TimetableEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableEntryService {

    private final TimetableEntryRepository timetableRepository;

    @Autowired
    public TimetableEntryService(TimetableEntryRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    // ✅ Get all timetable entries for a specific faculty
    public List<TimetableEntry> getTimetableByFacultyId(String facultyId) {
        return timetableRepository.findByFacultyUser_FacultyId(facultyId);
    }

    // ✅ (Optional) Get all timetable entries in the system
    public List<TimetableEntry> getAllTimetables() {
        return timetableRepository.findAll();
    }

    // ✅ (Optional) Save a single timetable entry (internal use)
    public TimetableEntry saveEntry(TimetableEntry entry) {
        return timetableRepository.save(entry);
    }

    // ✅ (Optional) Delete all entries for a faculty
    public void deleteTimetableByFacultyId(String facultyId) {
        List<TimetableEntry> entries = timetableRepository.findByFacultyUser_FacultyId(facultyId);
        timetableRepository.deleteAll(entries);
    }
}