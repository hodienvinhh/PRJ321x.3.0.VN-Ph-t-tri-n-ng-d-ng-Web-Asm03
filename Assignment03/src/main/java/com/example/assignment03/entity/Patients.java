package com.example.assignment03.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Patients")
@Data
public class Patients implements Serializable {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = " `STATUS`")
    private int status;

    @Column(name = " `NAME`")
    private String name;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = " DATE_OF_BIRTH")
    private String dateOfBirth;

    @Column(name = "PATHOLOGICAL")
    private String pathological;

    @Column(name = "`DESCRIBE`")
    private String describe;

    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID")
    private User doctor;
}
