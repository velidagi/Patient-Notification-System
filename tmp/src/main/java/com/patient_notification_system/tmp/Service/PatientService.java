package com.patient_notification_system.tmp.Service;
import com.patient_notification_system.tmp.Entity.Patient;
import com.patient_notification_system.tmp.Repository.PatientRepo;
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


