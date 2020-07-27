package ru.patientbase.mainAPI.dto;

import lombok.Data;
import ru.patientbase.mainAPI.entity.Doctor;

@Data
public class RegisterDto {

    private String name;
    private String email;
    private String password;

    public static Doctor getDoctor(RegisterDto registerDto) {
        Doctor doctor = new Doctor();
        doctor.setEmail(registerDto.getEmail());
        doctor.setName(registerDto.getName());
        doctor.setPassword(registerDto.getPassword());

        return doctor;
    }

}
