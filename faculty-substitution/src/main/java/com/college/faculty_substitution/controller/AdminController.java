package com.college.faculty_substitution.controller;

import com.college.faculty_substitution.model.Admin;
import com.college.faculty_substitution.repository.AdminRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminRepository repo;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Admin admin) {
        if (repo.findByUsername(admin.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        if (repo.findByEmail(admin.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        admin.setPassword(BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt()));
        repo.save(admin);
        return ResponseEntity.ok("Signup successful");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Admin request) {
        Optional<Admin> adminOpt = repo.findByUsername(request.getUsername());

        if (adminOpt.isEmpty()) {
            adminOpt = repo.findByEmail(request.getUsername()); // Try email
        }

        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            if (BCrypt.checkpw(request.getPassword(), admin.getPassword())) {
                return ResponseEntity.ok("Login successful");
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
