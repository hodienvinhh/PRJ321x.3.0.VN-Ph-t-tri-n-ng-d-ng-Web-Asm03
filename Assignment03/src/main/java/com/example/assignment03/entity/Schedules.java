package com.example.assignment03.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Schedules")
@Data
public class Schedules implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "`DATE`")
    private String date;

    @Column(name = "`TIME`")
    private String time;

    @Column(name = "MAX_BOOKING")
    private String maxBooking;

    @Column(name = "SUM_BOOKING")
    private String sumBooking;

    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID")
    private User doctorId;

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
