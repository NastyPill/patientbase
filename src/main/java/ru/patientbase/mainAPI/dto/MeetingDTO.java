package ru.patientbase.mainAPI.dto;

import lombok.Data;
import ru.patientbase.mainAPI.entity.Meeting;
import ru.patientbase.mainAPI.entity.Status;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Data
public class MeetingDTO {

    private Long id;
    private Date date;
    private String organisation;
    private String description;
    private List<String> links;
    private String status;
    private MiniPatientDTO patientDTO;

    public static Meeting getMeeting(MeetingDTO meetingDto) {
        Meeting meeting = new Meeting();
        meeting.setId(meetingDto.getId());
        meeting.setDate(meetingDto.getDate());
        meeting.setOrganisation(meetingDto.getOrganisation());
        meeting.setDescription(meetingDto.getDescription());
        meeting.setLinks(meetingDto.getLinks());
        meeting.setStatus(Status.valueOf(meetingDto.getStatus()));

        return meeting;
    }

    public static MeetingDTO translateToDto(Meeting meeting) {
        MeetingDTO meetingDTO = new MeetingDTO();
        meetingDTO.setId(meeting.getId());
        meetingDTO.setDate(meeting.getDate());
        meetingDTO.setOrganisation(meeting.getOrganisation());
        meetingDTO.setDescription(meeting.getDescription());
        meetingDTO.setLinks(meeting.getLinks());
        meetingDTO.setStatus(meeting.getStatus().toString());
        meetingDTO.setPatientDTO(MiniPatientDTO.translate(meeting.getPatient()));

        return meetingDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingDTO that = (MeetingDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(organisation, that.organisation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, organisation);
    }
}
