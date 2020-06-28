package ru.patientbase.mainAPI.service;

import ru.patientbase.mainAPI.entity.Doctor;

import java.util.NoSuchElementException;

public interface DoctorService {

    Doctor getDoctorById(Long id) throws NoSuchElementException;

    Doctor getDoctorByEmail(String email) throws NoSuchElementException;

    void modifyDoctor(Doctor doctor) throws NoSuchElementException;

}
