package ru.patientbase.mainAPI.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.patientbase.mainAPI.entity.Meeting;
import ru.patientbase.mainAPI.entity.Patient;
import ru.patientbase.mainAPI.entity.Status;
import ru.patientbase.mainAPI.repository.MeetingRepository;
import ru.patientbase.mainAPI.repository.PatientRepository;
import ru.patientbase.mainAPI.service.MeetingService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class MeetingServiceImpl implements MeetingService {

    final PatientRepository patientRepository;

    final MeetingRepository meetingRepository;

    public MeetingServiceImpl(PatientRepository patientRepository, MeetingRepository meetingRepository) {
        this.patientRepository = patientRepository;
        this.meetingRepository = meetingRepository;
    }

    @Override
    public void add(Long patientId, Meeting meeting) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (!optionalPatient.isPresent()) {
            throw new NoSuchElementException("There is no patient with id: " + patientId);
        }
        Patient patient = optionalPatient.get();
        Meeting saved = meetingRepository.save(meeting);
        patient.getMeetingList().add(saved);
        patientRepository.save(patient);
    }

    @Override
    public void delete(Long id) {
        Optional<Meeting> optionalMeeting = meetingRepository.findById(id);
        if (optionalMeeting.isPresent()) {
            Meeting meeting = optionalMeeting.get();
            meeting.setStatus(Status.DELETED);
            meetingRepository.save(meeting);
        } else {
            throw new NoSuchElementException("Meeting with id: " + id + " wasn't found");
        }
    }

    @Override
    public void modify(Meeting meeting) {
        Optional<Meeting> optionalMeeting = meetingRepository.findById(meeting.getId());
        if (optionalMeeting.isPresent()) {
            meetingRepository.save(meeting);
        } else {
            throw new NoSuchElementException("There is no meeting with id: " + meeting.getId());
        }
    }

    @Override
    public Meeting find(Long id) {
        Optional<Meeting> optionalMeeting = meetingRepository.findById(id);
        if (optionalMeeting.isPresent()) {
            return optionalMeeting.get();
        } else {
            throw new NoSuchElementException("There is no meeting with id: " + id);
        }
    }
}
