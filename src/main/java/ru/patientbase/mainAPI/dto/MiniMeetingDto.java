package ru.patientbase.mainAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.patientbase.mainAPI.entity.Meeting;

import java.sql.Date;

@Data
@AllArgsConstructor
public class MiniMeetingDto {
    private Long id;
    private String organisation;
    private Date date;


    public static MiniMeetingDto translate(Meeting meeting) {
        return new MiniMeetingDto(meeting.getId(),
                meeting.getOrganisation(),
                meeting.getDate());
    }

}
