package com.example.assignment03.service;

import com.example.assignment03.dto.PatientsDTO;

public interface ISchedulesService {
    PatientsDTO viewAppointmentsDoctorByAdmin(int id);
}
