package com.tdeas.patient_notifier.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "target_criteria",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "target_criteria_name")
        })
public class TargetCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "target_criteria_name", unique = true, nullable = false)

    private String target_criteria_name;
    private String message_text;

    public String getTargetCriteria() {
        return target_criteria_name;
    }

    public void setTargetCriteria(String target_criteria_name) {
        this.target_criteria_name = target_criteria_name;
    }

    public String getMessage() {
        return message_text;
    }
}
