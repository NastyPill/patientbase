package ru.patientbase.mainAPI.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.patientbase.mainAPI.dto.MeetingDTO;
import ru.patientbase.mainAPI.entity.Meeting;
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

    @PostMapping("meeting/new/{id}")
    public ResponseEntity<MeetingDTO> add(@RequestBody MeetingDTO meetingDTO, @PathVariable Long id) {
        meetingDTO.setStatus("ACTIVE");
        return ResponseEntity.ok(MeetingDTO
                .translateToDto(
                        meetingService
                                .add(id, MeetingDTO.getMeeting(meetingDTO))));
    }

}
