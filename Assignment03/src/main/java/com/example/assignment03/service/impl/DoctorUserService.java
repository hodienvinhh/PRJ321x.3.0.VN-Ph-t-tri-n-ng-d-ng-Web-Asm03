package com.example.assignment03.service.impl;

import com.example.assignment03.entity.DoctorUser;
import com.example.assignment03.repository.IDoctorUserRepository;
import com.example.assignment03.service.IDoctorUserService;
import com.example.assignment03.specification.specialization.ClinicsSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class DoctorUserService implements IDoctorUserService {
    @Autowired
    private IDoctorUserRepository doctorUserRepository;

    @Override
    public Page<DoctorUser> findSpecializationsByName(Pageable pageable, String search) {
        return null;
    }

}
