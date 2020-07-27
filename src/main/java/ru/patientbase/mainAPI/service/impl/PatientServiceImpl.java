package ru.patientbase.mainAPI.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.patientbase.mainAPI.entity.Doctor;
import ru.patientbase.mainAPI.entity.Patient;
import ru.patientbase.mainAPI.entity.Status;
import ru.patientbase.mainAPI.repository.DoctorRepository;
import ru.patientbase.mainAPI.repository.PatientRepository;
import ru.patientbase.mainAPI.service.PatientService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

    final PatientRepository patientRepository;
    final DoctorRepository doctorRepository;

    @Autowired
    public PatientServiceImpl(DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Patient addPatient(Patient patient) {
        if (!doctorRepository.findById(patient.getDoctor().getId()).isPresent()) {
            throw new NoSuchElementException("Doctor wasn't found");
        }
        if (patient.getName() == null || patient.getSurname() == null ||
                patient.getAddress() == null || patient.getDescription() == null ||
                patient.getDateOfBirth() == null) {
            throw new IllegalArgumentException("Some fields are null!");
        }
        return patientRepository.save(patient);
    }

    @Override
    public void modifyPatient(Patient patient) {
        if (patientRepository.findById(patient.getId()).isPresent()) {
            patientRepository.save(patient);
        } else {
            throw new NoSuchElementException("Such patient wasn't found");
        }
    }

    @Override
    public void deletePatient(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            patient.setStatus(Status.DELETED);
            patientRepository.save(patient);
        } else {
            throw new NoSuchElementException("Such patient wasn't found");
        }
    }

    @Override
    public Patient findPatient(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            return patient.get();
        } else {
            throw new NoSuchElementException("Patient with id: " + id + " wasn't found");
        }
    }

    @Override
    public List<Patient> getAll(Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (doctor.isPresent()) {
            return patientRepository.findAllByDoctor(doctor.get());
        } else {
            throw new NoSuchElementException("Doctor with id: " + id + " wasn't found");
        }
    }
}
