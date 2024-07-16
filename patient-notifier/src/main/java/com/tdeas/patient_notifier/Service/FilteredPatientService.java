package com.tdeas.patient_notifier.Service;

import com.tdeas.patient_notifier.Entity.FilteredPatient;
import com.tdeas.patient_notifier.Repository.FilteredPatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FilteredPatientService {

    @Autowired
    private FilteredPatientRepo filteredPatientRepo;

    public FilteredPatient saveFilteredPatient(FilteredPatient patient) {
        return filteredPatientRepo.save(patient);
    }

    public List<FilteredPatient> getAllFilteredPatients() {
        return filteredPatientRepo.findAll();
    }

    public void clearFilteredPatients() {
        filteredPatientRepo.deleteAll();
    }

    public List<FilteredPatient> getAllFiltered(Optional<Long> patientId) {
        if (patientId.isPresent())
            return filteredPatientRepo.findByPatientId(patientId.get());
        return filteredPatientRepo.findAll();
    }

    public FilteredPatient getOneFilteredById(long patientId) {
        return filteredPatientRepo.findById(patientId).orElse(null);
    }

    public FilteredPatient createOneFiltered(FilteredPatient newFiltered) {
        return filteredPatientRepo.save(newFiltered);
    }

}
