package com.tdeas.patient_notifier.Service;

import com.tdeas.patient_notifier.Entity.FilteredPatient;
import com.tdeas.patient_notifier.Entity.Patient;
import com.tdeas.patient_notifier.Entity.TargetCriteria;
import com.tdeas.patient_notifier.Repository.FilteredPatientRepo;
import com.tdeas.patient_notifier.Repository.PatientRepo;
import com.tdeas.patient_notifier.Repository.TargetCriteriaRepo;
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

    @Autowired
    private TargetCriteriaRepo targetCriteriaRepo;


    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<FilteredPatient> getAllFilteredPatients() {
        return filteredPatientRepository.findAll();
    }

    @Transactional
    public void addPatient(Patient patient) {
        patientRepository.save(patient);

        if ((patient.getAge() > 50 && patient.getAge() <= 69) && patient.getGender().equalsIgnoreCase("Male")) {
            TargetCriteria targetCriteria = targetCriteriaRepo.findById(1L)
                    .orElseThrow(() -> new RuntimeException("Colon Cancer criteria not found"));
            createFilteredPatient(patient, targetCriteria);
        }

        if ((patient.getAge() > 40 && patient.getAge() <= 69) && patient.getGender().equalsIgnoreCase("Female")) {
            TargetCriteria targetCriteria = targetCriteriaRepo.findById(2L)
                    .orElseThrow(() -> new RuntimeException("Breast Cancer criteria not found"));
            createFilteredPatient(patient, targetCriteria);
        }

        if (patient.getAge() > 18 && patient.getNotificationPreference().equalsIgnoreCase("SMS")) {
            TargetCriteria targetCriteria = targetCriteriaRepo.findById(3L)
                    .orElseThrow(() -> new RuntimeException("Stay Fit criteria not found"));
            createFilteredPatient(patient, targetCriteria);
        }
    }

    private void createFilteredPatient(Patient patient, TargetCriteria targetCriteria) {
        FilteredPatient filteredPatient = new FilteredPatient();
        filteredPatient.setPatient(patient);
        filteredPatient.setTargetCriteria(targetCriteria);
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



    @Transactional
    public void deletePatient(Long patientId) {
        filteredPatientRepository.deleteByPatientId(patientId);
        patientRepository.deleteById(patientId);
    }

}
