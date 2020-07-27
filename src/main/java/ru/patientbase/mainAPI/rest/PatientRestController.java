package ru.patientbase.mainAPI.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.patientbase.mainAPI.dto.PatientDTO;
import ru.patientbase.mainAPI.entity.Doctor;
import ru.patientbase.mainAPI.entity.Patient;
import ru.patientbase.mainAPI.entity.Status;
import ru.patientbase.mainAPI.service.DoctorService;
import ru.patientbase.mainAPI.service.PatientService;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("api/v1.0/")
public class PatientRestController {

    final PatientService patientService;
    final DoctorService doctorService;

    @Autowired
    public PatientRestController(DoctorService doctorService, PatientService patientService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @GetMapping("patient/all/{id}")
    public ResponseEntity<List<PatientDTO>> getAll(@PathVariable Long id) {
        log.info("Get all patients was invoked");
        return ResponseEntity.ok(patientService
                .getAll(id)
                .stream()
                .map(PatientDTO::translateToDto)
                .collect(Collectors.toList())
        );
    }

    @PostMapping("patient/add/{doctorId}")
    public ResponseEntity<PatientDTO> createPatient(@PathVariable Long doctorId, @RequestBody PatientDTO patientDTO) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        log.info("CreatePatient was invoked");
        if (doctor != null) {
            Patient patient = PatientDTO.getPatient(patientDTO);
            patient.setDoctor(doctorService.getDoctorById(doctorId));
            patient.setStatus(Status.ACTIVE);
            patient.setCreated(new Date(System.currentTimeMillis()));
            patient.setUpdated(new Date(System.currentTimeMillis()));
            Patient saved = patientService.addPatient(patient);
            return ResponseEntity.ok(PatientDTO.translateToDto(saved));
        } else {
            throw new NoSuchElementException("Doctor with id: " + doctorId + " wasn't found");
        }
    }

    @PatchMapping("patient/modify/{doctorId}")
    public ResponseEntity<PatientDTO> modifyPatient(@PathVariable Long doctorId, @RequestBody PatientDTO patientDTO) throws IllegalAccessException {
        Patient patient = patientService.findPatient(patientDTO.getId());
        if (!patientService.findPatient(patientDTO.getId()).getDoctor().getId().equals(doctorId)) {
            throw new IllegalAccessException("Patient doctor_id and doctor_id aren't equal");
        }
        if (patient != null) {
            patient.setName(patientDTO.getName());
            patient.setSurname(patientDTO.getSurname());
            patient.setLinks(patientDTO.getLinks());
            patient.setDescription(patientDTO.getDescription());
            patient.setDateOfBirth(patientDTO.getDateOfBirth());
            patient.setAddress(patientDTO.getAddress());
            patient.setUpdated(new Date(System.currentTimeMillis()));
            patientService.modifyPatient(patient);
            return ResponseEntity.ok(PatientDTO.translateToDto(patient));
        } else {
            throw new NoSuchElementException("Patient with id: " + patientDTO.getId() + " wasn't found");
        }
    }

    @DeleteMapping("patient/delete/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }

    @GetMapping("patient/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable Long id) {
        return ResponseEntity.ok(PatientDTO.translateToDto(patientService.findPatient(id)));
    }

}
