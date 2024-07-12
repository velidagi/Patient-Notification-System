package com.patient_notification_system.tmp.Controller;


import com.patient_notification_system.tmp.Entity.Patient;
import com.patient_notification_system.tmp.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/addPatient")
    public Patient postPatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }
}

