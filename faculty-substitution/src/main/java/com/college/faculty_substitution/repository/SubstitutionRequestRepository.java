package com.college.faculty_substitution.repository;

import com.college.faculty_substitution.model.SubstitutionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubstitutionRequestRepository extends JpaRepository<SubstitutionRequest, Long> {

    // Optional: Get requests made by a specific faculty
    List<SubstitutionRequest> findByRequestingFacultyId(String requestingFacultyId);

    // Optional: Get requests assigned to a specific substitute
    List<SubstitutionRequest> findBySubstituteFacultyId(String substituteFacultyId);

    // Optional: Get requests for a specific day and period
    List<SubstitutionRequest> findByDayAndPeriod(String day, int period);
}
