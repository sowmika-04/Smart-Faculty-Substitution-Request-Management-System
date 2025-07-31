package com.college.faculty_substitution.controller;

import com.college.faculty_substitution.model.FacultyUser;
import com.college.faculty_substitution.repository.FacultyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

@RestController
@RequestMapping("/faculty")
@CrossOrigin(origins = "*")
public class FacultyUserController {

    @Autowired
    private FacultyUserRepository repo;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody FacultyUser faculty) {
        if (repo.findByFacultyId(faculty.getFacultyId()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Faculty ID already exists");
        }

        faculty.setPassword(BCrypt.hashpw(faculty.getPassword(), BCrypt.gensalt()));
        repo.save(faculty);
        return ResponseEntity.ok("Signup successful");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody FacultyUser request) {
        System.out.println("Login attempt for: " + request.getFacultyId());

        Optional<FacultyUser> facultyOpt = repo.findByFacultyId(request.getFacultyId());

        if (facultyOpt.isPresent()) {
            FacultyUser faculty = facultyOpt.get();
            System.out.println("Stored hash: " + faculty.getPassword());
            System.out.println("Entered password: " + request.getPassword());

            if (BCrypt.checkpw(request.getPassword(), faculty.getPassword())) {
                System.out.println("Password matched.");
                return ResponseEntity.ok("Login successful");
            } else {
                System.out.println("Password mismatch.");
            }
        } else {
            System.out.println("Faculty ID not found.");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}

