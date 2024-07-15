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

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }

    @GetMapping("/findByName/{name}")
    public List<Patient> findPatientsByName(@PathVariable String name) {
        return patientRepo.findByName(name);
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
