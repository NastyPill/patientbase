package ru.patientbase.mainAPI.staticTests;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.patientbase.mainAPI.dto.DoctorDTO;
import ru.patientbase.mainAPI.entity.Doctor;
import ru.patientbase.mainAPI.entity.Status;

import java.sql.Date;

public class DtoTest {

    @Test
    void doctorDto() {
        DoctorDTO doctorDTO1 = new DoctorDTO();
        doctorDTO1.setUpdated(new Date(1L));
        doctorDTO1.setCreated(new Date(1L));
        doctorDTO1.setStatus(Status.ACTIVE.toString());
        doctorDTO1.setPatientList(null);
        doctorDTO1.setPassword("qwertytrewq");
        doctorDTO1.setEmail("ya@ya.ru");
        doctorDTO1.setName("John");
        doctorDTO1.setId(2L);
        Doctor doctor1 = new Doctor();
        doctor1.setUpdated(new Date(1L));
        doctor1.setCreated(new Date(1L));
        doctor1.setStatus(Status.ACTIVE);
        doctor1.setPatientList(null);
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
        doctor2.setPatientList(null);
        doctor2.setPassword("qwertytrewq");
        doctor2.setEmail("yaneya@ya.ru");
        doctor2.setName("Johnny");
        doctor2.setId(2L);
        Assert.assertNotEquals(DoctorDTO.translateToDto(doctor2), doctorDTO1); //test for 2 not equals dto
    }

}
