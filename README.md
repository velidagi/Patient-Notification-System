# Patient Notifier System
## Project Development Process

This project has been a true journey of learning and development for me. At the outset, my knowledge of Java and backend/frontend was zero, and I lacked the necessary experience to complete such a project. I had to learn many concepts from scratch and put in intensive effort to bring the project to fruition. I am extremely pleased with the outcomes of the project now, and it has been a rewarding experience.
## Overview

This project is a Spring Boot application designed to manage patient information and send notifications based on specific criteria. It includes functionalities to add, update, delete, and search patients, as well as filter and notify patients based on defined criteria.

## Requirements

- Java: Oracle OpenJDK 22.0.1
- Spring Boot: 3.3.1
- Maven
- MySQL: 8.0.34

## Usage

The application provides a RESTful API to manage patients. Below are the available endpoints and their usage. (You can also check out "Postman Repository".)

## API Endpoints

### Get All Patients
- **URL**: `/patients`
- **Method**: `GET`
- **Description**: Retrieves all patients.
- **Response**: `List<Patient>`

### Get Filtered Patients
- **URL**: `/patients/filtered`
- **Method**: `GET`
- **Description**: Retrieves all filtered patients based on criteria.
- **Response**: `List<FilteredPatient>`

### Add a New Patient
- **URL**: `/patients`
- **Method**: `POST`
- **Description**: Adds a new patient.
- **Request Body**: `Patient`
- **Response**: `void`

### Update an Existing Patient
- **URL**: `/patients/{id}`
- **Method**: `PUT`
- **Description**: Updates an existing patient by ID.
- **Request Body**: `Patient`
- **Response**: `ResponseEntity<?>`

### Delete a Patient
- **URL**: `/patients/{id}`
- **Method**: `DELETE`
- **Description**: Deletes a patient by ID.
- **Response**: `void`

### Find Patients by Name
- **URL**: `/patients/findByName/{name}`
- **Method**: `GET`
- **Description**: Finds patients by name.
- **Response**: `List<Patient>`

### Search Patients with Multiple Criteria
- **URL**: `/patients/search`
- **Method**: `GET`
- **Description**: Searches patients with multiple criteria (name, gender, age range).
- **Response**: `List<Patient>`

### Find Patients by Age Range
- **URL**: `/patients/findByAgeRange/{minAge}/{maxAge}`
- **Method**: `GET`
- **Description**: Finds patients by age range.
- **Response**: `List<Patient>`

### Find Patients by Notification Preference
- **URL**: `/patients/findByNotificationPreference/{preference}`
- **Method**: `GET`
- **Description**: Finds patients by notification preference.
- **Response**: `List<Patient>`

### Send Notifications to Patients
- **URL**: `/patients/sendNotifications`
- **Method**: `POST`
- **Description**: Simulates a system that sends notifications to patients based on their preferences and lists successful delivery results.
- **Response**: `ResponseEntity<List<NotificationResult>>`



## Project Structure

## Entities Overview

### Patient
Purpose: This class represents patient data. Stores basic information (name, date of birth, gender, etc.) and contact preferences (SMS, email, etc.) for each patient.

### FilteredPatient
Purpose: This class represents patients filtered according to certain criteria and creates a notification template. For example, patients of appropriate age and gender for colon cancer screening.
Defined criteria:
Stay Healthy: Patients over 18 years of age.
Breast Cancer: Female patients between the ages of 40 and 69.
Colon Cancer: Male patients between the ages of 40 and 70.

### PatientLog
Purpose: This class is used to log change history of patients. Every update, addition or deletion of patient data is recorded in this log. It contains information such as version number and reason for change.

### TargetCriteria
Purpose: This class represents specific screening or notification criteria. Contains the criteria and the messages to be sent for the criteria.


## Methods
- **`createFilteredPatient(Patient patient, TargetCriteria targetCriteria)`**: Creates a `FilteredPatient` entity when a patient meets the criteria.
- **`logChange(Patient patient, int versionNumber, Long nextPatientId, String changeReason)`**: Logs the details of patient changes.
- **`sendNotifications()`**: Iterates through `FilteredPatient` entities and sends notifications based on their preferences.
- **`logNotificationDetails(Long patientId, String patientName, String patientGender, String notificationType, String message)`**: Logs the details of sent notifications.
- **`sendSmsNotification(String phoneNumber, String message)`**: Placeholder for sending SMS notifications.
- **`sendEmailNotification(String email, String message)`**: Placeholder for sending email notifications.

## Conclusion

This application manages patient data and sends notifications based on defined criteria. It provides robust functionalities for CRUD operations on patients and detailed logging for tracking changes and notifications. The use of filtering criteria ensures targeted notifications to relevant patients.

