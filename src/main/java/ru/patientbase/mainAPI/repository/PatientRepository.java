package ru.patientbase.mainAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.patientbase.mainAPI.entity.Doctor;
import ru.patientbase.mainAPI.entity.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findAllByDoctor(Doctor doctor);

}
