package com.tdeas.patient_notifier.Entity;

public class NotificationResult {
    private Long patientId;
    private String name;
    private String gender;
    private String notificationType;
    private String message;
    private String phoneNumber;
    private String mail;
    private String criteriaName;
    private Integer age;

    public NotificationResult(Long patientId, String patientName, String patientGender, String notificationType, String messageText, String phoneNumber,String mail, String criteriaName, Integer age) {
        this.patientId = patientId;
        this.name = patientName;
        this.gender = patientGender;
        this.notificationType = notificationType;
        this.message = messageText;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.criteriaName = criteriaName;
        this.age = age;
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

    public Integer getAge() {
        return age;
    }

    public void setName(Integer setAge) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
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
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}


