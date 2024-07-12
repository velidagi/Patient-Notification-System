package com.patient_notification_system.tmp.Entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class NotificationPreferences {
    private boolean sms;
    private boolean email;
    private boolean none;
}
