package com.tdeas.patient_notifier.Repository;

import com.tdeas.patient_notifier.Entity.TargetCriteria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TargetCriteriaRepo extends JpaRepository<TargetCriteria, Long> {

}

