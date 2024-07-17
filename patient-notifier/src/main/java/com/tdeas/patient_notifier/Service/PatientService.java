package com.tdeas.patient_notifier.Service;

import com.tdeas.patient_notifier.Entity.*;
import com.tdeas.patient_notifier.Repository.FilteredPatientRepo;
import com.tdeas.patient_notifier.Repository.PatientLogRepo;
import com.tdeas.patient_notifier.Repository.PatientRepo;
import com.tdeas.patient_notifier.Repository.TargetCriteriaRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class PatientService {
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepo patientRepository;

    @Autowired
    private FilteredPatientRepo filteredPatientRepository;

    @Autowired
    private PatientLogRepo patientLogRepo;

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
        logChange(patient, 1, "Added");

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
    private void logChange(Patient patient, int versionNumber, String changeReason) {
        PatientLog patientLog = new PatientLog();
        patientLog.setPatient(patient);
        patientLog.setId(patient.getId());
        patientLog.setName(patient.getName());
        patientLog.setBirthDate(patient.getBirthDate());
        patientLog.setGender(patient.getGender());
        patientLog.setNationalId(patient.getNationalId());
        patientLog.setPassportNumber(patient.getPassportNumber());
        patientLog.setEmail(patient.getEmail());
        patientLog.setPhoneNumber(patient.getPhoneNumber());
        patientLog.setNotificationPreference(patient.getNotificationPreference());
        patientLog.setVersionNumber(versionNumber);
        patientLog.setChangeReason(changeReason);

        patientLogRepo.save(patientLog);
    }

    public List<NotificationResult> sendNotifications() {
        List<FilteredPatient> filteredNotifications = filteredPatientRepository.findAll();
        List<NotificationResult> notificationResults = new ArrayList<>();
        int size = filteredNotifications.size();
        System.out.println("filteredNotifications listesi " + size + " eleman içeriyor.");
        for (FilteredPatient notification : filteredNotifications) {
            String messageText = notification.getTargetCriteria().getMessage();
            String criteriaName = notification.getTargetCriteria().getTargetCriteria();
            String patientName = notification.getPatient().getName();
            String patientGender = notification.getPatient().getGender();
            String notificationType = notification.getNotificationPreference();
            String phoneNumber = notification.getPhoneNumber();
            String mail = notification.getEmail();
            Integer age = notification.getAge();

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
                    messageText,
                    phoneNumber,
                    mail,
                    criteriaName,
                    age
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
    public void updatePatient(Long id, Patient updatedPatient) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        existingPatient.setName(updatedPatient.getName());
        existingPatient.setBirthDate(updatedPatient.getBirthDate());
        existingPatient.setGender(updatedPatient.getGender());
        existingPatient.setNationalId(updatedPatient.getNationalId());
        existingPatient.setPassportNumber(updatedPatient.getPassportNumber());
        existingPatient.setEmail(updatedPatient.getEmail());
        existingPatient.setPhoneNumber(updatedPatient.getPhoneNumber());
        existingPatient.setNotificationPreference(updatedPatient.getNotificationPreference());

        patientRepository.save(existingPatient);
        Optional<PatientLog> existingLogs = patientLogRepo.findById(existingPatient.getId());

        int nextVersionNumber = existingLogs.stream()
                .mapToInt(PatientLog::getVersionNumber)
                .max()
                .orElse(0) + 1;
        logChange(existingPatient, nextVersionNumber +1, "Updated");

        filteredPatientRepository.deleteByPatient(existingPatient);

        if ((existingPatient.getAge() > 50 && existingPatient.getAge() <= 69) && existingPatient.getGender().equalsIgnoreCase("Male")) {
            TargetCriteria targetCriteria = targetCriteriaRepo.findById(1L)
                    .orElseThrow(() -> new RuntimeException("Colon Cancer criteria not found"));
            createFilteredPatient(existingPatient, targetCriteria);
        }

        if ((existingPatient.getAge() > 40 && existingPatient.getAge() <= 69) && existingPatient.getGender().equalsIgnoreCase("Female")) {
            TargetCriteria targetCriteria = targetCriteriaRepo.findById(2L)
                    .orElseThrow(() -> new RuntimeException("Breast Cancer criteria not found"));
            createFilteredPatient(existingPatient, targetCriteria);
        }

        if (existingPatient.getAge() > 18 && existingPatient.getNotificationPreference().equalsIgnoreCase("SMS")) {
            TargetCriteria targetCriteria = targetCriteriaRepo.findById(3L)
                    .orElseThrow(() -> new RuntimeException("Stay Fit criteria not found"));
            createFilteredPatient(existingPatient, targetCriteria);
        }
    }
    @Transactional
    public void deletePatient(Long patientId) {
        Optional<Patient> existingPatient = patientRepository.findById(patientId);
        if (existingPatient.isPresent()) {
            Patient patient = existingPatient.get();
            filteredPatientRepository.deleteByPatientId(patientId);
            patientRepository.deleteById(patientId);
            Optional<PatientLog> existingLogs = patientLogRepo.findById(patientId);

            int nextVersionNumber = existingLogs.stream()
                    .mapToInt(PatientLog::getVersionNumber)
                    .max()
                    .orElse(0) + 1;
            logChange(patient, nextVersionNumber, "Deleted");

        }
    }

}
