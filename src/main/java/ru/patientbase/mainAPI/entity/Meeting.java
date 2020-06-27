package ru.patientbase.mainAPI.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "MEETING")
public class Meeting extends BaseEntity {

    @Column(name = "date")
    private Date date;

    @Column(name = "organisation")
    private String organisation;

    @Column(name = "description")
    private String description;

    @Type(type = "list-array")
    @Column(
            name = "photos",
            columnDefinition = "text[]"
    )
    private List<String> links;

    @ManyToOne
    @JoinTable(name = "patient_to_meeting",
            joinColumns = {
                    @JoinColumn(name = "patient_id", referencedColumnName = "id"),
                    @JoinColumn(name = "meeting_id", referencedColumnName = "id")
            })
    private Patient patient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(date, meeting.date) &&
                Objects.equals(organisation, meeting.organisation) &&
                Objects.equals(description, meeting.description) &&
                Objects.equals(patient, meeting.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), date, organisation, description, patient);
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "date=" + date +
                ", organisation='" + organisation + '\'' +
                ", description='" + description + '\'' +
                ", patient=" + patient.getSurname() + " " + patient.getName() +
                '}';
    }
}
