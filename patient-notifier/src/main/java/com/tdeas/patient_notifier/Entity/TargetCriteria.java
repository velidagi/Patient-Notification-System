package com.tdeas.patient_notifier.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class TargetCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "target_criteria_name")
    private String target_criteria_name;
    private String message_text;

    public String getTargetCriteria() {
        return target_criteria_name;
    }

    public void setTargetCriteria(String target_criteria_name) {
        this.target_criteria_name = target_criteria_name;
    }
}
