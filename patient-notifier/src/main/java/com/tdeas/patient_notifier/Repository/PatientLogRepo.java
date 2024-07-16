package com.tdeas.patient_notifier.Repository;

import com.tdeas.patient_notifier.Entity.PatientLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientLogRepo extends JpaRepository<PatientLog, Long> {
}
