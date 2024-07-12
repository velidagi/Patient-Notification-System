package com.tdeas.patient_notifier.Controller;


import com.tdeas.patient_notifier.Entity.Patient;
import com.tdeas.patient_notifier.Repository.PatientRepo;
import com.tdeas.patient_notifier.Service.PatientService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private PatientService patientService;


    @GetMapping
    public List<Patient> getAllUsers(){
        return patientRepo.findAll();
    }


    @PostMapping("/addPatient")
    public Patient postPatient(@RequestBody Patient newPatient) {
        return patientService.savePatient(newPatient);
    }

    @GetMapping("/{patientId}")
    public Patient getOnePatient(@PathVariable Long patientId){
        return patientRepo.findById(patientId).orElse(null);
    }

    @PutMapping("/{patientId}")
    public Patient updateOnePatient(@PathVariable long patientId, @RequestBody Patient newPatient){
        Optional<Patient> patient = patientRepo.findById(patientId);
        if (patient.isPresent()) //Patient'ın olup olmadığını kontrol eder. Varsa if'e girer.
        {
            Patient foundPatient = patient.get();
            foundPatient.setName(newPatient.getName());
            foundPatient.setGender(newPatient.getGender());
            foundPatient.setBirthDate(newPatient.getBirthDate());
            foundPatient.setEmail(newPatient.getEmail());
            foundPatient.setNationalId(newPatient.getNationalId());
            foundPatient.setNotificationPreference(newPatient.getNotificationPreference());
            foundPatient.setPassportNumber(newPatient.getPassportNumber());
            foundPatient.setPhoneNumber(newPatient.getPhoneNumber());
            patientRepo.save(foundPatient);
            return foundPatient;
        }else
            return null;
    }

    @DeleteMapping("/{patientId}")
    public void deleteOnePatient(@PathVariable long patientId){
        patientRepo.deleteById(patientId);
    }

}