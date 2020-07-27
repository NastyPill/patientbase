package ru.patientbase.mainAPI.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.patientbase.mainAPI.dto.DoctorDTO;
import ru.patientbase.mainAPI.dto.LoginDto;
import ru.patientbase.mainAPI.dto.RegisterDto;
import ru.patientbase.mainAPI.entity.Doctor;
import ru.patientbase.mainAPI.service.DoctorService;

@RestController
@RequestMapping("/api/v1.0/")
@Slf4j
public class DoctorRestController {

    final DoctorService doctorService;
    final PasswordEncoder passwordEncoder;

    @Autowired
    public DoctorRestController(DoctorService doctorService, PasswordEncoder passwordEncoder) {
        this.doctorService = doctorService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("register")
    public void register(@RequestBody RegisterDto registerDto) {
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        doctorService.register(RegisterDto.getDoctor(registerDto));
    }

    @PostMapping("login")
    public ResponseEntity<DoctorDTO> login(@RequestBody LoginDto loginDto) {
        Doctor doctor = doctorService.getDoctorByEmail(loginDto.getEmail());
        if(passwordEncoder.matches(loginDto.getPassword(), doctor.getPassword())) {
            return ResponseEntity.ok(DoctorDTO.translateToDto(doctor));
        } else {
            throw new IllegalArgumentException("Invalid username/password");
        }
    }

    @GetMapping("doctor/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        log.info("getDoctorById with id: " + id);
        return ResponseEntity.ok(DoctorDTO.translateToDto(doctor));
    }

    @PatchMapping("doctor/modify")
    public void modifyDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = DoctorDTO.getDoctor(doctorDTO);
        doctorService.modifyDoctor(doctor);
    }
}
