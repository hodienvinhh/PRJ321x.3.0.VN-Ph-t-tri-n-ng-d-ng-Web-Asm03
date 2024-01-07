package com.example.assignment03.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Places")
@Data
public class Places implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = " NAME_PALACES")
    private String namePalaces;

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
