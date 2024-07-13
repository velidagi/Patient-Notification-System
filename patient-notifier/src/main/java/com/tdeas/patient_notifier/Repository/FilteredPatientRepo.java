package com.tdeas.patient_notifier.Repository;

import com.tdeas.patient_notifier.Entity.FilteredPatient;
import com.tdeas.patient_notifier.Entity.Patient;
import com.tdeas.patient_notifier.dto.FilterRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilteredPatientRepo extends JpaRepository<FilteredPatient, Long> {
    @Query("SELECT p FROM Patient p WHERE " +
            "(:#{#filterRequest.name} IS NULL OR p.name = :#{#filterRequest.name}) AND " +
            "(:#{#filterRequest.minAge} IS NULL OR FUNCTION('getAge', p.birthDate) >= :#{#filterRequest.minAge}) AND " +
            "(:#{#filterRequest.maxAge} IS NULL OR FUNCTION('getAge', p.birthDate) <= :#{#filterRequest.maxAge}) AND " +
            "(:#{#filterRequest.notificationPreference} IS NULL OR p.notificationPreference = :#{#filterRequest.notificationPreference})")
    List<Patient> findFilteredPatients(FilterRequest filterRequest);
}
