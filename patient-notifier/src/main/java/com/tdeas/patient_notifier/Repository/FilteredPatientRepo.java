package com.tdeas.patient_notifier.Repository;

import com.tdeas.patient_notifier.Entity.FilteredPatient;
import com.tdeas.patient_notifier.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilteredPatientRepo extends JpaRepository<FilteredPatient, Long> {
    // Delete filtered patient records
    void deleteByPatientId(Long patientId);
    void deleteByPatient(Patient patient);

    // Find filtered patient records by patient ID
    List<FilteredPatient> findByPatientId(long l);
}
