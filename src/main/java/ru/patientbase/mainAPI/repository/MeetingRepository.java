package ru.patientbase.mainAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.patientbase.mainAPI.entity.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

}
