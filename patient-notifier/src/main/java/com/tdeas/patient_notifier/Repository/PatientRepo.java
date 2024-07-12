package com.tdeas.patient_notifier.Repository;

import com.tdeas.patient_notifier.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, Long> {
}
