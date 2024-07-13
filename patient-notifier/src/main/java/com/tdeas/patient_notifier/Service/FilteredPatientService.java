package com.tdeas.patient_notifier.Service;

import com.tdeas.patient_notifier.Entity.FilteredPatient;
import com.tdeas.patient_notifier.Repository.FilteredPatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
