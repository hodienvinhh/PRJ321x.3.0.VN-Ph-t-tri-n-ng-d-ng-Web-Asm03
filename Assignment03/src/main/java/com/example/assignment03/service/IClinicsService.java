package com.example.assignment03.service;

import com.example.assignment03.dto.IClinicsDTO;
import com.example.assignment03.entity.Clinics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClinicsService {
    List<IClinicsDTO> getTopClinics();

    Page<Clinics> getClinicByName(Pageable pageable, String search);
}
