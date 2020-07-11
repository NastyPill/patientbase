package ru.patientbase.mainAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.patientbase.mainAPI.entity.Doctor;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByEmail(String email);

    @Modifying
    @Query("update Doctor u set u.name = :name, u.email = :email where u.id = :id")
    Integer modify(@Param("name") String name, @Param("email") String email, @Param("id") Long id);

}
