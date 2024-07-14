package com.tdeas.patient_notifier.Service;

import com.tdeas.patient_notifier.Entity.FilteredPatient;
import com.tdeas.patient_notifier.Entity.Patient;
import com.tdeas.patient_notifier.Repository.FilteredPatientRepo;
import com.tdeas.patient_notifier.Repository.PatientRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PatientService {

    @Autowired
    private PatientRepo patientRepository;

    @Autowired
    private FilteredPatientRepo filteredPatientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<FilteredPatient> getAllFilteredPatients() {
        return filteredPatientRepository.findAll();
    }

    @Transactional
    public void addPatient(Patient patient) {
        patientRepository.save(patient);

        if (patient.getAge() > 25 && patient.getGender().equalsIgnoreCase("Male")) {
            // Create a filtered patient entry
            FilteredPatient filteredPatient = new FilteredPatient();
            filteredPatient.setPatient(patient);
            filteredPatient.setName(patient.getName());
            filteredPatient.setBirthDate(patient.getBirthDate());
            filteredPatient.setGender(patient.getGender());
            filteredPatient.setNationalId(patient.getNationalId());
            filteredPatient.setPassportNumber(patient.getPassportNumber());
            filteredPatient.setEmail(patient.getEmail());
            filteredPatient.setPhoneNumber(patient.getPhoneNumber());
            filteredPatient.setNotificationPreference(patient.getNotificationPreference());

            filteredPatientRepository.save(filteredPatient);
        }
    }

    @Transactional
    public void deletePatient(Long patientId) {
        filteredPatientRepository.deleteByPatientId(patientId);
        patientRepository.deleteById(patientId);
    }

}
