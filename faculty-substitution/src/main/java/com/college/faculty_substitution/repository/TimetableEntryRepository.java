package com.college.faculty_substitution.repository;

import com.college.faculty_substitution.model.FacultyUser;
import com.college.faculty_substitution.model.TimetableEntry;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimetableEntryRepository extends JpaRepository<TimetableEntry, Long> {

    List<TimetableEntry> findByFacultyUser_FacultyId(String facultyId);

    @Query("SELECT t.facultyUser FROM TimetableEntry t WHERE t.day = :day AND t.period = :period")
    List<FacultyUser> findBusyFaculty(@Param("day") String day, @Param("period") int period);
}
