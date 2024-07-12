package com.tdeas.patient_notifier.Service;

import com.tdeas.patient_notifier.Entity.Patient;
import com.tdeas.patient_notifier.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepository;

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }
}