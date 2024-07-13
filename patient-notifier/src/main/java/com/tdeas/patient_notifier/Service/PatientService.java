package com.tdeas.patient_notifier.Service;

import com.tdeas.patient_notifier.Entity.Patient;
import com.tdeas.patient_notifier.Entity.FilteredPatient;
import com.tdeas.patient_notifier.Repository.PatientRepo;
import com.tdeas.patient_notifier.Repository.FilteredPatientRepo;
import com.tdeas.patient_notifier.dto.FilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private FilteredPatientRepo filteredPatientRepo;

    public Patient savePatient(Patient patient) {
        Patient savedPatient = patientRepo.save(patient);
        updateFilteredDatabase(savedPatient);
        return savedPatient;
    }

    public void updateFilteredDatabase(Patient patient) {
        // Filtreleme kriterlerini burada belirleyin
        if (shouldBeFiltered(patient)) {
            FilteredPatient filteredPatient = new FilteredPatient();
            filteredPatient.setName(patient.getName());
            filteredPatient.setBirthDate(patient.getBirthDate());
            filteredPatient.setGender(patient.getGender());
            filteredPatient.setNotificationPreference(patient.getNotificationPreference());
            filteredPatientRepo.save(filteredPatient);
        }
    }

    private boolean shouldBeFiltered(Patient patient) {
        // Filtreleme koşullarını burada belirleyin (örneğin, belirli bir yaş aralığı veya bildirim tercihi)
        return patient.getNotificationPreference().equals("SMS") &&
                getAge(patient.getBirthDate()) >= 20 &&
                getAge(patient.getBirthDate()) <= 25;
    }

    private int getAge(Date birthDate) {
        // Yaşı hesaplamak için basit bir yöntem
        LocalDate birth = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(birth, LocalDate.now()).getYears();
    }
    public List<Patient> filterPatients(FilterRequest filterRequest) {
        // Filtreleme mantığını burada uygulayın
        return patientRepo.findFilteredPatients(filterRequest);
    }
}
