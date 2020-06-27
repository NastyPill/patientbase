package ru.patientbase.mainAPI.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "DOCTOR")
@Data
public class Doctor extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<Patient> patientList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Doctor doctor = (Doctor) o;
        return email.equals(doctor.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", patientList=" + patientList +
                '}';
    }
}
