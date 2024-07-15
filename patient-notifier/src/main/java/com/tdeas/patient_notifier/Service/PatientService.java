package com.tdeas.patient_notifier.Service;

import com.tdeas.patient_notifier.Entity.FilteredPatient;
import com.tdeas.patient_notifier.Entity.NotificationResult;
import com.tdeas.patient_notifier.Entity.Patient;
import com.tdeas.patient_notifier.Entity.TargetCriteria;
import com.tdeas.patient_notifier.Repository.FilteredPatientRepo;
import com.tdeas.patient_notifier.Repository.PatientRepo;
import com.tdeas.patient_notifier.Repository.TargetCriteriaRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class PatientService {
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepo patientRepository;

    @Autowired
    private FilteredPatientRepo filteredPatientRepository;

    @Autowired
    private TargetCriteriaRepo targetCriteriaRepo;


    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<FilteredPatient> getAllFilteredPatients() {
        return filteredPatientRepository.findAll();
    }

    @Transactional
    public void addPatient(Patient patient) {
        patientRepository.save(patient);

        if ((patient.getAge() > 50 && patient.getAge() <= 69) && patient.getGender().equalsIgnoreCase("Male")) {
            TargetCriteria targetCriteria = targetCriteriaRepo.findById(1L)
                    .orElseThrow(() -> new RuntimeException("Colon Cancer criteria not found"));
            createFilteredPatient(patient, targetCriteria);
        }

        if ((patient.getAge() > 40 && patient.getAge() <= 69) && patient.getGender().equalsIgnoreCase("Female")) {
            TargetCriteria targetCriteria = targetCriteriaRepo.findById(2L)
                    .orElseThrow(() -> new RuntimeException("Breast Cancer criteria not found"));
            createFilteredPatient(patient, targetCriteria);
        }

        if (patient.getAge() > 18 && patient.getNotificationPreference().equalsIgnoreCase("SMS")) {
            TargetCriteria targetCriteria = targetCriteriaRepo.findById(3L)
                    .orElseThrow(() -> new RuntimeException("Stay Fit criteria not found"));
            createFilteredPatient(patient, targetCriteria);
        }
    }

    private void createFilteredPatient(Patient patient, TargetCriteria targetCriteria) {
        FilteredPatient filteredPatient = new FilteredPatient();
        filteredPatient.setPatient(patient);
        filteredPatient.setTargetCriteria(targetCriteria);
        filteredPatient.setName(patient.getName());
        filteredPatient.setBirthDate(patient.getBirthDate());
        filteredPatient.setGender(patient.getGender());
        filteredPatient.setNationalId(patient.getNationalId());
        filteredPatient.setPassportNumber(patient.getPassportNumber());
        filteredPatient.setEmail(patient.getEmail());
        filteredPatient.setPhoneNumber(patient.getPhoneNumber());
        filteredPatient.setNotificationPreference(patient.getNotificationPreference());

        filteredPatientRepository.save(filteredPatient);
    }

    public List<NotificationResult> sendNotifications() {
        List<FilteredPatient> filteredNotifications = filteredPatientRepository.findAll();
        List<NotificationResult> notificationResults = new ArrayList<>();
        int size = filteredNotifications.size();
        System.out.println("filteredNotifications listesi " + size + " eleman içeriyor.");
        for (FilteredPatient notification : filteredNotifications) {
            String messageText = notification.getTargetCriteria().getMessage();
            String patientName = notification.getPatient().getName();
            String patientGender = notification.getPatient().getGender();
            String notificationType = notification.getNotificationPreference();

            // Send notification based on preference
            if (notificationType.equals("Sms")) {
                sendSmsNotification(notification.getPatient().getPhoneNumber(), messageText);
            } else if (notificationType.equals("Mail")) {
                sendEmailNotification(notification.getPatient().getEmail(), messageText);
            }

            // Collect notification results
            NotificationResult result = new NotificationResult(
                    notification.getPatient().getId(),
                    patientName,
                    patientGender,
                    notificationType,
                    messageText
            );
            notificationResults.add(result);
        }

        return notificationResults;
    }

    private void logNotificationDetails(Long patientId, String patientName, String patientGender, String notificationType, String message) {
        logger.info("Notification sent to Patient ID: {}, Name: {}, Gender: {}, via {}: {}",
                patientId, patientName, patientGender, notificationType, message);
    }

    private void sendSmsNotification(String phoneNumber, String message) {
        // SMS gönderme işlemini gerçekleştirin
    }

    private void sendEmailNotification(String email, String message) {
        // E-posta gönderme işlemini gerçekleştirin
    }

    @Transactional
    public void deletePatient(Long patientId) {
        filteredPatientRepository.deleteByPatientId(patientId);
        patientRepository.deleteById(patientId);
    }

}
