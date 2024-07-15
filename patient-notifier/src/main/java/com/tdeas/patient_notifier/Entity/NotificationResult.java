package com.tdeas.patient_notifier.Entity;

public class NotificationResult {
    private Long patientId;
    private String name;
    private String gender;
    private String notificationType;
    private String message;

    public NotificationResult(Long patientId, String patientName, String patientGender, String notificationType, String messageText) {
        this.patientId = patientId;
        this.name = patientName;
        this.gender = patientGender;
        this.notificationType = notificationType;
        this.message = messageText;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


