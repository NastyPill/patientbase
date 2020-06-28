package ru.patientbase.mainAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "PATIENT")
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class Patient extends BaseEntity {

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @Type(type = "list-array")
    @Column(
            name = "photos",
            columnDefinition = "text[]"
    )
    private List<String> links;

    @JsonIgnore
    @ManyToOne
    @JoinTable(name = "doctor_to_patient",
            joinColumns = {@JoinColumn(name = "doctor_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "patient_id", referencedColumnName = "id")}
    )
    private Doctor doctor;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Meeting> meetingList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(surname, patient.surname) &&
                Objects.equals(name, patient.name) &&
                Objects.equals(dateOfBirth, patient.dateOfBirth) &&
                Objects.equals(address, patient.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), surname, name, dateOfBirth, address);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", meetingList=" + meetingList +
                '}';
    }
}
