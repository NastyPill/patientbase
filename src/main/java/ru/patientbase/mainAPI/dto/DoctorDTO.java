package ru.patientbase.mainAPI.dto;

import lombok.Data;
import ru.patientbase.mainAPI.entity.Doctor;
import ru.patientbase.mainAPI.entity.Patient;
import ru.patientbase.mainAPI.entity.Status;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Data
public class DoctorDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Patient> patientList;

    private String status;
    private Date created;
    private Date updated;

    public static DoctorDTO translateToDto(Doctor doctor) {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctor.getId());
        doctorDTO.setName(doctor.getName());
        doctorDTO.setEmail(doctor.getEmail());
        doctorDTO.setPassword(doctor.getPassword());
        doctorDTO.setPatientList(doctor.getPatientList());
        doctorDTO.setStatus(doctor.getStatus().toString());
        doctorDTO.setCreated(doctor.getCreated());
        doctorDTO.setUpdated(doctor.getUpdated());
        return doctorDTO;
    }

    public static Doctor getDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setId(doctorDTO.getId());
        doctor.setName(doctorDTO.getName());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setPassword(doctorDTO.getPassword());
        doctor.setPatientList(doctorDTO.getPatientList());
        doctor.setStatus(Status.valueOf(doctorDTO.getStatus()));
        doctor.setCreated(doctorDTO.getCreated());
        doctor.setUpdated(doctorDTO.getUpdated());
        return doctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorDTO doctorDTO = (DoctorDTO) o;
        return Objects.equals(id, doctorDTO.id) &&
                Objects.equals(name, doctorDTO.name) &&
                Objects.equals(email, doctorDTO.email) &&
                Objects.equals(password, doctorDTO.password) &&
                Objects.equals(patientList, doctorDTO.patientList) &&
                Objects.equals(status, doctorDTO.status) &&
                Objects.equals(created, doctorDTO.created) &&
                Objects.equals(updated, doctorDTO.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, patientList, status, created, updated);
    }

    @Override
    public String toString() {
        return "DoctorDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
