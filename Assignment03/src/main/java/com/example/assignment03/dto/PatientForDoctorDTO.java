package com.example.assignment03.dto;

import lombok.Data;

@Data
public class PatientForDoctorDTO {
    private String doctorName;
    private Integer id;
    private String name;
    private String gender;
    private String address;
    private String pathological;
    private String describe;
}
