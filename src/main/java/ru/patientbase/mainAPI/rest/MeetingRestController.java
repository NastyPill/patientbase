package ru.patientbase.mainAPI.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.patientbase.mainAPI.dto.MeetingDTO;
import ru.patientbase.mainAPI.service.impl.MeetingServiceImpl;

@RestController
@RequestMapping("api/v1.0")
public class MeetingRestController {

    final
    MeetingServiceImpl meetingService;

    @Autowired
    public MeetingRestController(MeetingServiceImpl meetingService) {
        this.meetingService = meetingService;
    }


    @GetMapping("/meeting/{id}")
    public ResponseEntity<MeetingDTO> getMeeting(@PathVariable Long id) {
        return ResponseEntity.ok(MeetingDTO.translateToDto(meetingService.find(id)));
    }

}
