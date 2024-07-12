package com.patient_notification_system.tmp.Entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ContactInfo {
    private String test; // örn: "email", "phone"
    private String value;
}
