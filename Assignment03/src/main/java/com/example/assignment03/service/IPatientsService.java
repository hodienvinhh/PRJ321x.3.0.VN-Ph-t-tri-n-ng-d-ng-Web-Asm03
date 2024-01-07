package com.example.assignment03.service;

import com.example.assignment03.dto.PatientForDoctorDTO;
import com.example.assignment03.dto.PatientsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPatientsService {

    Page<PatientForDoctorDTO> getListPatientByDoctorId(Pageable pageable);


}
