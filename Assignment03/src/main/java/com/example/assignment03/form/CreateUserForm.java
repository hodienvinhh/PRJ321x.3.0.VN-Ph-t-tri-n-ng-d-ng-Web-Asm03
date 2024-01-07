package com.example.assignment03.form;

import lombok.Data;

@Data
public class CreateUserForm {
    private int id;
    private String name;
    private String gender;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String generalIntroduction;// Giới thiệu chung
    private String trainingProcess;// Quá trình đào tạo
    private String achievementsAchieved;// Các thành tựu đạt được
    private String specialties;// Các chuyên khoa phụ trách


}
