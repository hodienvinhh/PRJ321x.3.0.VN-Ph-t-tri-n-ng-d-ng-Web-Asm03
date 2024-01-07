package com.example.assignment03.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "`Users`")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = " `NAME`")
    private String name;

    @Column(name = " EMAIL")
    private String email;

    @Column(name = "`PASSWORD`")
    private String password;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "AVATAR")
    private String avatar;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "`DESCRIPTION`")
    private String description;

    @Column(name = "IS_ACTIVE")
    private int isActive;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @Column(name = "GENERALINTRODUCTION")
    private String generalIntroduction;// Giới thiệu chung

    @Column(name = "TRAININGPROCESS")
    private String trainingProcess;// Quá trình đào tạo

    @Column(name = "ACHIEVEMENTSACHIEVED")
    private String achievementsAchieved;// Các thành tựu đạt được

    @Column(name = "SPECIALTIES")
    private String specialties;// Các chuyên khoa phụ trách

    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(name = "DELETE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteAt;

    @Column(name = "UPDATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @Column(unique = true)
    private String token;

}
