package com.tdeas.patient_notifier.Repository;

import com.tdeas.patient_notifier.Entity.FilteredPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilteredPatientRepo extends JpaRepository<FilteredPatient, Long> {
    void deleteByPatientId(Long patientId);

    List<FilteredPatient> findByPatientId(long l);
}
