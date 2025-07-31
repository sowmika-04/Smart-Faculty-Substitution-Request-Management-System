package com.college.faculty_substitution.controller;

import com.college.faculty_substitution.dto.TimetableRequestDTO;
import com.college.faculty_substitution.model.FacultyUser;
import com.college.faculty_substitution.model.TimetableEntry;
import com.college.faculty_substitution.repository.FacultyUserRepository;
import com.college.faculty_substitution.repository.TimetableEntryRepository;
import com.college.faculty_substitution.service.TimetableEntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/timetable")
@CrossOrigin(origins = "*")
public class TimetableEntryController {

    @Autowired
    private FacultyUserRepository facultyUserRepo;

    @Autowired
    private TimetableEntryRepository timetableRepo;

    @Autowired
    private TimetableEntryService timetableService;

    // ✅ Save the full timetable
    @PostMapping("/save")
    public ResponseEntity<String> saveTimetable(@RequestBody TimetableRequestDTO dto) {
        String facultyId = dto.getFacultyId();
        String name = dto.getFacultyName();
        String email = dto.getFacultyEmail();

        // Find or create faculty user
        FacultyUser faculty = facultyUserRepo.findByFacultyId(facultyId).orElseGet(() -> {
            FacultyUser newUser = new FacultyUser();
            newUser.setFacultyId(facultyId);
            newUser.setName(name);
            newUser.setEmail(email);
            return facultyUserRepo.save(newUser);
        });

        // Delete previous entries
        timetableRepo.deleteAll(timetableRepo.findByFacultyUser_FacultyId(facultyId));

        // Save new entries
        Map<String, List<List<String>>> timetable = dto.getTimetable();
        for (Map.Entry<String, List<List<String>>> entry : timetable.entrySet()) {
            String day = entry.getKey();
            List<List<String>> periods = entry.getValue();  // [["Maths", "CSE-A"], ...]

            for (int i = 0; i < periods.size(); i++) {
                List<String> slot = periods.get(i);
                String subject = slot.get(0);
                String className = slot.get(1);

                if ((subject != null && !subject.isBlank()) || (className != null && !className.isBlank())) {
                    TimetableEntry newEntry = new TimetableEntry(day, i + 1, subject, className, faculty);
                    timetableRepo.save(newEntry);
                }
            }
        }

        return ResponseEntity.ok("Timetable saved successfully");
    }

    // ✅ Get timetable by faculty ID
    @GetMapping("/{facultyId}")
    public ResponseEntity<List<TimetableEntry>> getTimetableByFaculty(@PathVariable String facultyId) {
        List<TimetableEntry> timetable = timetableService.getTimetableByFacultyId(facultyId);
        return ResponseEntity.ok(timetable);
    }

    // ✅ Optional: Get all saved timetable entries
    @GetMapping("/all")
    public ResponseEntity<List<TimetableEntry>> getAllTimetables() {
        return ResponseEntity.ok(timetableService.getAllTimetables());
    }

    @GetMapping("/available")
    public List<Map<String, String>> getAvailableFaculty(@RequestParam String day, @RequestParam int period) {
        List<FacultyUser> all = facultyUserRepo.findAll();
        List<FacultyUser> busy = timetableRepo.findBusyFaculty(day, period); // implement this

        List<Map<String, String>> free = new ArrayList<>();

        for (FacultyUser f : all) {
            if (!busy.contains(f)) {
                Map<String, String> info = new HashMap<>();
                info.put("facultyId", f.getFacultyId());
                info.put("name", f.getName());
                info.put("subject", f.getSubject());
                info.put("email", f.getEmail()); // <-- include email
                free.add(info);
            }
        }

        return free;
    }

}

