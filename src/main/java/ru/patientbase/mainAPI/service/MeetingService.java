package ru.patientbase.mainAPI.service;

import ru.patientbase.mainAPI.entity.Meeting;

public interface MeetingService {

    void add(Long patientId, Meeting meeting);

    void delete(Long id);

    void modify(Meeting meeting);

    Meeting find(Long id);

}
