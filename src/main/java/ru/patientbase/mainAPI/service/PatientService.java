package ru.patientbase.mainAPI.service;

import ru.patientbase.mainAPI.entity.Patient;

import java.util.List;

public interface PatientService {

    void addPatient(Patient patient);

    void modifyPatient(Patient patient);

    void deletePatient(Long id);

    Patient findPatient(Long id);

    List<Patient> getAll(Long id);
}
