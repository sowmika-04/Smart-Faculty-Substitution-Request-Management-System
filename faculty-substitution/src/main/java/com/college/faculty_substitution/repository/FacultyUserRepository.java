package com.college.faculty_substitution.repository;

import com.college.faculty_substitution.model.FacultyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacultyUserRepository extends JpaRepository<FacultyUser, Long> {
    Optional<FacultyUser> findByFacultyId(String facultyId);
}