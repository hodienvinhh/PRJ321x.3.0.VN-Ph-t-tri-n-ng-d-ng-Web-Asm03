package com.example.assignment03.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Comments")
@Data
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TIMEBOOKING")
    private String timeBooking;

    @Column(name = "DATEBOOKING")
    private String dateBooking;

    @Column(name = " `NAME`")
    private String name;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "`STATUS`")
    private int status;

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
