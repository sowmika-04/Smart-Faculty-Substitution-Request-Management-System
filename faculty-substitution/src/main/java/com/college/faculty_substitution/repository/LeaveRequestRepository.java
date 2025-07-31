package com.college.faculty_substitution.repository;

import com.college.faculty_substitution.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {

    List<LeaveRequest> findByFacultyId(String facultyId);

    List<LeaveRequest> findBySubstituteId(String substituteId);

    List<LeaveRequest> findByDayAndPeriod(String day,int period);
}