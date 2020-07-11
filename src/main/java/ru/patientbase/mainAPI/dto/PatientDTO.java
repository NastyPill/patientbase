package ru.patientbase.mainAPI.dto;

import lombok.Data;
import ru.patientbase.mainAPI.entity.Patient;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class PatientDTO {

    private Long id;
    private String surname;
    private String name;
    private Date dateOfBirth;
    private String address;
    private String description;
    private List<String> links;
    private Set<MiniMeetingDto> meetings;
    private String status;

    public static Patient getPatient(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setId(patientDTO.getId());
        patient.setSurname(patientDTO.getSurname());
        patient.setName(patientDTO.getName());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setAddress(patientDTO.getAddress());
        patient.setDescription(patientDTO.getDescription());
        patient.setLinks(patientDTO.getLinks());

        return patient;
    }

    public static PatientDTO translateToDto(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setSurname(patient.getSurname());
        patientDTO.setName(patient.getName());
        patientDTO.setDateOfBirth(patient.getDateOfBirth());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setDescription(patient.getDescription());
        patientDTO.setLinks(patient.getLinks());
        patientDTO.setStatus(patient.getStatus().toString());
        if (patient.getMeetingList() != null)
            patientDTO.setMeetings(patient.getMeetingList().stream()
                    .map(MiniMeetingDto::translate)
                    .collect(Collectors.toSet()));

        return patientDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDTO that = (PatientDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, dateOfBirth, address);
    }

}
