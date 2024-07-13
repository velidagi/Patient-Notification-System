package com.tdeas.patient_notifier.Repository;

import com.tdeas.patient_notifier.Entity.Patient;
import com.tdeas.patient_notifier.dto.FilterRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient, Long> {
    List<Patient> findByName(String name);
    @Query("SELECT p FROM Patient p WHERE YEAR(CURRENT_DATE) - YEAR(p.birthDate) BETWEEN :minAge AND :maxAge")
    List<Patient> findByAgeRange(@Param("minAge") int minAge, @Param("maxAge") int maxAge);
    List<Patient> findByNotificationPreference(String preference);
    @Query("SELECT p FROM Patient p WHERE " +
            "(:#{#filterRequest.name} IS NULL OR p.name = :#{#filterRequest.name}) AND " +
            "(:#{#filterRequest.minAge} IS NULL OR FUNCTION('getAge', p.birthDate) >= :#{#filterRequest.minAge}) AND " +
            "(:#{#filterRequest.maxAge} IS NULL OR FUNCTION('getAge', p.birthDate) <= :#{#filterRequest.maxAge}) AND " +
            "(:#{#filterRequest.notificationPreference} IS NULL OR p.notificationPreference = :#{#filterRequest.notificationPreference})")
    List<Patient> findFilteredPatients(FilterRequest filterRequest);
}
