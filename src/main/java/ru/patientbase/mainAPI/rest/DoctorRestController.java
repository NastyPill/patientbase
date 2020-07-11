package ru.patientbase.mainAPI.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.patientbase.mainAPI.dto.DoctorDTO;
import ru.patientbase.mainAPI.entity.Doctor;
import ru.patientbase.mainAPI.service.DoctorService;

@RestController
@RequestMapping("/api/v1.0/")
@Slf4j
public class DoctorRestController {

    final DoctorService doctorService;

    @Autowired
    public DoctorRestController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("doctor/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(DoctorDTO.translateToDto(doctor));
    }

    @PatchMapping("doctor/modify")
    public void modifyDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = DoctorDTO.getDoctor(doctorDTO);
        doctorService.modifyDoctor(doctor);
    }
}
