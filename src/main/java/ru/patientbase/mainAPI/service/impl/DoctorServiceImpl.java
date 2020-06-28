package ru.patientbase.mainAPI.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.patientbase.mainAPI.entity.Doctor;
import ru.patientbase.mainAPI.repository.DoctorRepository;
import ru.patientbase.mainAPI.service.DoctorService;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public Doctor getDoctorById(Long id) throws NoSuchElementException {
        return doctorRepository
                .findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Doctor getDoctorByEmail(String email) throws NoSuchElementException {
        return doctorRepository
                .findByEmail(email)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void modifyDoctor(Doctor doctor) throws NoSuchElementException {
        doctorRepository
                .findById(doctor.getId())
                .orElseThrow(NoSuchElementException::new);
        doctorRepository.save(doctor);
    }
}
