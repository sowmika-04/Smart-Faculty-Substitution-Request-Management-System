package com.college.faculty_substitution.controller;

import com.college.faculty_substitution.model.FacultyUser;
import com.college.faculty_substitution.model.SubstitutionRequest;
import com.college.faculty_substitution.repository.FacultyUserRepository;
import com.college.faculty_substitution.repository.SubstitutionRequestRepository;
import com.college.faculty_substitution.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/substitution")
@CrossOrigin(origins = "*")
public class SubstitutionRequestController {

    @Autowired
    private SubstitutionRequestRepository substitutionRepo;

    @Autowired
    private FacultyUserRepository facultyRepo;

    @Autowired
    private EmailService emailService;

    @PostMapping("/request")
    public ResponseEntity<String> sendRequest(@RequestBody SubstitutionRequest req) {
        FacultyUser requester = facultyRepo.findByFacultyId(req.getRequestingFacultyId()).orElse(null);
        FacultyUser substitute = facultyRepo.findByFacultyId(req.getSubstituteFacultyId()).orElse(null);

        if (requester == null || substitute == null) {
            return ResponseEntity.badRequest().body("Faculty not found");
        }

        // Assign values
        req.setSubject(requester.getSubject());
        req.setClassName("Classroom-X"); // set dynamically if needed

        substitutionRepo.save(req); // save first to get ID

        String acceptLink = "http://localhost:8080/substitution/respond?requestId=" + req.getId() + "&response=accept";
        String declineLink = "http://localhost:8080/substitution/respond?requestId=" + req.getId() + "&response=decline";

        String body = String.format(
                "Hi %s,\n\nYou have a substitution request:\n\nFrom: %s (%s)\nSubject: %s\nClass: %s\nDay: %s, Period: %d\n\nPlease respond:\nAccept: %s\nDecline: %s",
                substitute.getName(),
                requester.getName(), requester.getEmail(),
                req.getSubject(), req.getClassName(),
                req.getDay(), req.getPeriod(),
                acceptLink, declineLink
        );

        emailService.sendEmail(substitute.getEmail(), "Substitution Request", body);
        return ResponseEntity.ok("Request sent successfully to " + substitute.getEmail());
    }

    @GetMapping("/respond")
    public ResponseEntity<String> handleResponse(@RequestParam Long requestId, @RequestParam String response) {
        SubstitutionRequest req = substitutionRepo.findById(requestId).orElse(null);
        if (req == null) return ResponseEntity.badRequest().body("Invalid request ID");

        FacultyUser requester = facultyRepo.findByFacultyId(req.getRequestingFacultyId()).orElse(null);
        FacultyUser substitute = facultyRepo.findByFacultyId(req.getSubstituteFacultyId()).orElse(null);

        if (requester == null || substitute == null) {
            return ResponseEntity.badRequest().body("Faculty info not found");
        }

        String subject = "Substitution Request " + (response.equalsIgnoreCase("accept") ? "Accepted" : "Declined");
        String body = String.format(
                "Hi %s,\n\nYour substitution request to %s (%s) was *%s*.\n\nDetails:\nSubject: %s\nClass: %s\nDay: %s, Period: %d",
                requester.getName(),
                substitute.getName(), substitute.getEmail(),
                response.toUpperCase(),
                req.getSubject(), req.getClassName(), req.getDay(), req.getPeriod()
        );

        emailService.sendEmail(requester.getEmail(), subject, body);
        return ResponseEntity.ok("Response recorded and sender notified");
    }
}
