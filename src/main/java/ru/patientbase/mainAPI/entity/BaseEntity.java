package ru.patientbase.mainAPI.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;
}
