package com.tdeas.patient_notifier.Controller;


import com.tdeas.patient_notifier.Entity.Patient;
import com.tdeas.patient_notifier.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/addPatient")
    public String postPatient(@RequestBody Patient patient) {
        patientService.savePatient(patient);
        return "POSTED";
    }
}