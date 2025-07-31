package com.college.faculty_substitution.service;

import com.college.faculty_substitution.model.SubstitutionRequest;
import com.college.faculty_substitution.repository.SubstitutionRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubstitutionRequestService {

    @Autowired
    private SubstitutionRequestRepository requestRepository;

    public SubstitutionRequest saveRequest(SubstitutionRequest request) {
        return requestRepository.save(request);
    }

    public List<SubstitutionRequest> getRequestsByRequestingFaculty(String facultyId) {
        return requestRepository.findByRequestingFacultyId(facultyId);
    }

    public List<SubstitutionRequest> getRequestsForSubstitute(String facultyId) {
        return requestRepository.findBySubstituteFacultyId(facultyId);
    }

    public List<SubstitutionRequest> getRequestsByDayAndPeriod(String day, int period) {
        return requestRepository.findByDayAndPeriod(day, period);
    }

    public List<SubstitutionRequest> getAllRequests() {
        return requestRepository.findAll();
    }
}
