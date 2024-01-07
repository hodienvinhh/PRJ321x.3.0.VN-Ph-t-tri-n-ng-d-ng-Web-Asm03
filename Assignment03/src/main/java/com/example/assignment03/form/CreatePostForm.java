package com.example.assignment03.form;

import com.example.assignment03.entity.Clinics;
import com.example.assignment03.entity.Patients;
import com.example.assignment03.entity.Specializations;
import com.example.assignment03.entity.User;
import lombok.Data;

import java.util.Date;
@Data
public class CreatePostForm {
    private int id;
    private String title;
    private int doctorId;
    private String dateTimeBooking;
    private int clinicsId;
    private int specializationsId;
    private String namePatient;
    private String gender;
    private String phone;
    private String address;
    private String dateOfBirth;
    private String pathological;

}
