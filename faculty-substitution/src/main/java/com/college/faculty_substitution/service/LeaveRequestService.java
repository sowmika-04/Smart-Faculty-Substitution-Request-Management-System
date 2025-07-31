package com.college.faculty_substitution.service;

import com.college.faculty_substitution.model.LeaveRequest;
import com.college.faculty_substitution.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service   // ✅ Same here
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    public List<LeaveRequest> getAllRequests() {
        return leaveRequestRepository.findAll();
    }

    public Optional<LeaveRequest> getRequestById(int id) {
        return leaveRequestRepository.findById(id);
    }

    public List<LeaveRequest> getRequestsByFacultyId(String facultyId) {
        return leaveRequestRepository.findByFacultyId(facultyId);
    }

    public List<LeaveRequest> getRequestsBySubstituteId(String substituteId) {
        return leaveRequestRepository.findBySubstituteId(substituteId);
    }

    public LeaveRequest addOrUpdateLeaveRequest(LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }

    public void deleteLeaveRequest(int id) {
        leaveRequestRepository.deleteById(id);
    }

    public List<LeaveRequest> getRequestsByDayAndPeriod(String day, int period) {
        return leaveRequestRepository.findByDayAndPeriod(day, period);
}
}
