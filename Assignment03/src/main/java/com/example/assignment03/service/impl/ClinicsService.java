package com.example.assignment03.service.impl;

import com.example.assignment03.dto.IClinicsDTO;
import com.example.assignment03.entity.Clinics;
import com.example.assignment03.repository.IClinicsRepository;
import com.example.assignment03.service.IClinicsService;
import com.example.assignment03.specification.specialization.ClinicsSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicsService implements IClinicsService {
    @Autowired
    private IClinicsRepository clinicsRepository;
    @Override
    public List<IClinicsDTO> getTopClinics() {
        return clinicsRepository.getTopClinics();
    }

    @Override
    public Page<Clinics> getClinicByName(Pageable pageable, String search) {
        Specification<Clinics> where = ClinicsSpecification.buildWhere(search);
        return clinicsRepository.findAll(where, pageable);
    }

}
