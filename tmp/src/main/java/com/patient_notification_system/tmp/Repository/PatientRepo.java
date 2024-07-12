package com.patient_notification_system.tmp.Repository;
import com.patient_notification_system.tmp.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, Long> {
}
