package com.example.assignment03.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Posts")
@Data
public class Posts implements Serializable {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENMARKDOWN")
    private String contentMarkdown;

    @Column(name = "CONTENHTML")
    private String contentHtml;

    @ManyToOne
    @JoinColumn(name = "FOR_DOCTOR_ID")
    private User doctorId;

    @ManyToOne
    @JoinColumn(name = "FOR_SPECIALIZATION_ID")
    private Specializations specializations;

    @ManyToOne
    @JoinColumn(name = "FOR_CLINIC_ID")
    private Clinics clinics;

    @ManyToOne
    @JoinColumn(name = "FOR_PATIENT_ID")
    private  Patients patients;

    @Column(name = "DATE_TIME_BOOKING")
    private Date dateTimeBooking;

    @Column(name = "CONFIRM_BY_DOCTOR")
    private int confirmByDoctor;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(name = "DELETE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteAt;

    @Column(name = "UPDATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
}
