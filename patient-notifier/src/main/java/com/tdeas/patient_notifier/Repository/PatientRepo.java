package com.tdeas.patient_notifier.Repository;

import com.tdeas.patient_notifier.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient, Long> {
    // Find patients by exact name match
    List<Patient> findByName(String name);

    // Find patients by name containing specified string, ignoring case
    List<Patient> findByNameContainingIgnoreCase(String name);

    // Find patients by gender using a custom query
    @Query("SELECT p FROM Patient p WHERE p.gender = :gender")
    List<Patient> findByGender(@Param("gender") String gender);

    // Find patients by age range using a custom query
    @Query("SELECT p FROM Patient p WHERE YEAR(CURRENT_DATE) - YEAR(p.birthDate) BETWEEN :minAge AND :maxAge")
    List<Patient> findByAgeRange(@Param("minAge") int minAge, @Param("maxAge") int maxAge);

    // Find patients by notification preference
    List<Patient> findByNotificationPreference(String preference);

}
