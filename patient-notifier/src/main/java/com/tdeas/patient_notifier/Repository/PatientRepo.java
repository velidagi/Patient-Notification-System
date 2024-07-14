package com.tdeas.patient_notifier.Repository;

import com.tdeas.patient_notifier.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient, Long> {
    List<Patient> findByName(String name);
    @Query("SELECT p FROM Patient p WHERE YEAR(CURRENT_DATE) - YEAR(p.birthDate) BETWEEN :minAge AND :maxAge")
    List<Patient> findByAgeRange(@Param("minAge") int minAge, @Param("maxAge") int maxAge);
    List<Patient> findByNotificationPreference(String preference);

}
