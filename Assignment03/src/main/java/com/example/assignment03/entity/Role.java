package com.example.assignment03.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Roles")
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ROLE_NAME")
    private String roleName;
}
