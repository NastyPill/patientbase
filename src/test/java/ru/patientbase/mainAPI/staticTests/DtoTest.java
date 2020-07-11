package ru.patientbase.mainAPI.staticTests;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.patientbase.mainAPI.dto.DoctorDTO;
import ru.patientbase.mainAPI.dto.MeetingDTO;
import ru.patientbase.mainAPI.dto.PatientDTO;
import ru.patientbase.mainAPI.entity.Doctor;
import ru.patientbase.mainAPI.entity.Meeting;
import ru.patientbase.mainAPI.entity.Patient;
import ru.patientbase.mainAPI.entity.Status;

import java.sql.Date;
import java.util.LinkedList;

public class DtoTest {

    @Test
    void runTests() {
        doctorDtoTest();
        patientDtoTest();
        meetingDtoTest();
    }


    @Test
    void doctorDtoTest() {
        DoctorDTO doctorDTO1 = new DoctorDTO();
        doctorDTO1.setUpdated(new Date(1L));
        doctorDTO1.setCreated(new Date(1L));
        doctorDTO1.setStatus(Status.ACTIVE.toString());
        doctorDTO1.setPatientList(new LinkedList<>());
        doctorDTO1.setEmail("ya@ya.ru");
        doctorDTO1.setName("John");
        doctorDTO1.setId(2L);
        Doctor doctor1 = new Doctor();
        doctor1.setUpdated(new Date(1L));
        doctor1.setCreated(new Date(1L));
        doctor1.setStatus(Status.ACTIVE);
        doctor1.setPatientList(new LinkedList<>());
        doctor1.setPassword("qwertytrewq");
        doctor1.setEmail("ya@ya.ru");
        doctor1.setName("John");
        doctor1.setId(2L);
        Assert.assertEquals(doctor1, DoctorDTO.getDoctor(doctorDTO1)); //Test for dto -> doctor
        DoctorDTO doctorDTO2 = DoctorDTO.translateToDto(doctor1);
        Assert.assertEquals(DoctorDTO.translateToDto(doctor1), doctorDTO2); //Test for 2 dto's from one source
        Assert.assertEquals(doctorDTO2, doctorDTO1); //Test for 2 equals dto's
        Doctor doctor2 = DoctorDTO.getDoctor(doctorDTO2);
        doctor2.setUpdated(new Date(1L));
        doctor2.setCreated(new Date(1L));
        doctor2.setStatus(Status.ACTIVE);
        doctor2.setPatientList(new LinkedList<>());
        doctor2.setPassword("qwertytrewq");
        doctor2.setEmail("yaneya@ya.ru");
        doctor2.setName("Johnny");
        doctor2.setId(2L);
        Assert.assertNotEquals(DoctorDTO.translateToDto(doctor2), doctorDTO1); //test for 2 not equals dto
    }

    @Test
    void patientDtoTest() {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setLinks(new LinkedList<>());
        patientDTO.setAddress("123456");
        patientDTO.setDateOfBirth(new Date(1l));
        patientDTO.setDescription("desc");
        patientDTO.setId(1l);
        patientDTO.setName("valera");
        patientDTO.setStatus(Status.ACTIVE.toString());
        patientDTO.setSurname("bes");
        Patient patient = new Patient();
        patient.setLinks(new LinkedList<>());
        patient.setAddress("123456");
        patient.setDateOfBirth(new Date(1l));
        patient.setDescription("desc");
        patient.setId(1l);
        patient.setName("valera");
        patient.setStatus(Status.ACTIVE);
        patient.setSurname("bes");
        Assert.assertEquals(patientDTO, PatientDTO.translateToDto(patient));
        Assert.assertEquals(PatientDTO.getPatient(patientDTO), patient);
        patient.setName("petya");
        Assert.assertNotEquals(patientDTO, PatientDTO.translateToDto(patient));
        Assert.assertNotEquals(PatientDTO.getPatient(patientDTO), patient);
        Assert.assertEquals(patientDTO, PatientDTO.translateToDto(PatientDTO.getPatient(patientDTO)));
    }

    @Test
    void meetingDtoTest() {
        MeetingDTO meetingDTO = new MeetingDTO();
        meetingDTO.setDate(new Date(2l));
        meetingDTO.setDescription("12345");
        meetingDTO.setId(1l);
        meetingDTO.setLinks(new LinkedList<>());
        meetingDTO.setOrganisation("org");
        meetingDTO.setStatus(Status.ACTIVE.toString());
        Meeting meeting = new Meeting();
        meeting.setDate(new Date(2l));
        meeting.setDescription("12345");
        meeting.setId(1l);
        meeting.setLinks(new LinkedList<>());
        meeting.setOrganisation("org");
        meeting.setStatus(Status.ACTIVE);

        Assert.assertEquals(meetingDTO, MeetingDTO.translateToDto(meeting));
        Assert.assertEquals(MeetingDTO.getMeeting(meetingDTO), meeting);
        meeting.setOrganisation("123222");
        Assert.assertNotEquals(meetingDTO, MeetingDTO.translateToDto(meeting));
        Assert.assertNotEquals(MeetingDTO.getMeeting(meetingDTO), meeting);
        Assert.assertEquals(meetingDTO, MeetingDTO.translateToDto(MeetingDTO.getMeeting(meetingDTO)));
    }

}
