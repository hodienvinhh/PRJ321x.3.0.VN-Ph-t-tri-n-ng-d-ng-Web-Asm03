package com.example.assignment03.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Appointments")
@Data
public class Appointments implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "PATHOLOGICAL")
    private String pathological;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID")
    private Patients patients;

    @ManyToOne
    @JoinColumn(name = "PLACE_ID")
    private Places places;

    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID")
    private User doctorId;

    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(name = "UPDATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
}
