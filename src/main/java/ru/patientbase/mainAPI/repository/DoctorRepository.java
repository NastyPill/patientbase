package ru.patientbase.mainAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.patientbase.mainAPI.entity.Doctor;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByEmail(String email);

}
