package com.tdeas.patient_notifier.Controller;

import com.tdeas.patient_notifier.Entity.FilteredPatient;
import com.tdeas.patient_notifier.Entity.NotificationResult;
import com.tdeas.patient_notifier.Entity.Patient;
import com.tdeas.patient_notifier.Repository.PatientRepo;
import com.tdeas.patient_notifier.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepo patientRepo;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/filtered")
    public List<FilteredPatient> getAllFilteredPatients() {
        return patientService.getAllFilteredPatients();
    }

    @PostMapping
    public void addPatient(@RequestBody Patient patient) {
        patientService.addPatient(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        patientService.updatePatient(id, updatedPatient);
        return ResponseEntity.ok("Patient updated successfully");
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }

    @GetMapping("/findByName/{name}")
    public List<Patient> findPatientsByName(@PathVariable String name) {
        return patientRepo.findByName(name);
    }
    @GetMapping("/search")
    public List<Patient> searchPatients(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "minAge", required = false) Integer minAge,
            @RequestParam(value = "maxAge", required = false) Integer maxAge
    ) {
        List<Patient> patients;

        if (name != null && !name.isEmpty()) {
            patients = patientRepo.findByNameContainingIgnoreCase(name);
        } else if (gender != null && !gender.isEmpty()) {
            patients = patientRepo.findByGender(gender);
        } else if (minAge != null && maxAge != null) {
            patients = patientRepo.findByAgeRange(minAge, maxAge);
        } else {
            patients = patientRepo.findAll(); // or return empty list
        }

        return patients;
    }
    @GetMapping("/findByAgeRange/{minAge}/{maxAge}")
    public List<Patient> findPatientsByAgeRange(@PathVariable int minAge, @PathVariable int maxAge) {
        return patientRepo.findByAgeRange(minAge, maxAge);
    }
    @GetMapping("/findByNotificationPreference/{preference}")
    public List<Patient> findPatientsByNotificationPreference(@PathVariable String preference) {
        return patientRepo.findByNotificationPreference(preference);
    }

    @PostMapping("/sendNotifications")
    public ResponseEntity<List<NotificationResult>> sendNotifications() {
        try {
            List<NotificationResult> results = patientService.sendNotifications();
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
