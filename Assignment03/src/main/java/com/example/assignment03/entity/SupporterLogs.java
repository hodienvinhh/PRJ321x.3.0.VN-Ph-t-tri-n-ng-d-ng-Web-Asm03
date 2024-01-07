package com.example.assignment03.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SupporterLogs")
@Data
public class SupporterLogs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name = " PATIENT_ID")
    private Patients patients;

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
