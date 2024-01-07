package com.example.assignment03.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Clinics")
@Data
public class Clinics implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = " `NAME`")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "INTRODUCTIONHTML")
    private String introductionHtml;

    @Column(name = "INTRODUCTIONMARKDOWN")
    private String introductionMarkDown;

    @Column(name = "`DESCRIPTION`")
    private String description;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "COST_OF_EXAMINATION")
    private String costOfExamination;

    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
}
