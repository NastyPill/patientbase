package ru.patientbase.mainAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.patientbase.mainAPI.entity.Patient;

import java.sql.Date;

@Data
@AllArgsConstructor
public class MiniPatientDTO {

    private Long id;
    private String surname;
    private String name;
    private Date dateOfBirth;
    private String address;

    public static MiniPatientDTO translate(Patient patient) {
        return new MiniPatientDTO(patient.getId(),
                patient.getSurname(),
                patient.getName(),
                patient.getDateOfBirth(),
                patient.getAddress());
    }

}
