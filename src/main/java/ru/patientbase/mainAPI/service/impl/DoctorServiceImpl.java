package ru.patientbase.mainAPI.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.patientbase.mainAPI.entity.Doctor;
import ru.patientbase.mainAPI.repository.DoctorRepository;
import ru.patientbase.mainAPI.service.DoctorService;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public Doctor getDoctorById(Long id) throws NoSuchElementException {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (doctor.isPresent()) {
            return doctor.get();
        } else {
            throw new NoSuchElementException("Such doctor wasn't found");
        }
    }

    @Override
    public Doctor getDoctorByEmail(String email) throws NoSuchElementException {
        Optional<Doctor> doctor = doctorRepository.findByEmail(email);
        if (doctor.isPresent()) {
            return doctor.get();
        } else {
            throw new NoSuchElementException("Such doctor wasn't found");
        }
    }

    @Override
    @Transactional
    public void modifyDoctor(Doctor doctor) throws NoSuchElementException {
        Optional<Doctor> doc = doctorRepository.findById(doctor.getId());
        if (doc.isPresent()) {
            doctorRepository.modify(doctor.getName(), doctor.getEmail(), doctor.getId());
        } else {
            throw new NoSuchElementException("Such doctor wasn't found");
        }
    }
}
