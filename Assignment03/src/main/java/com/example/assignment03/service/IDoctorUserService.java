package com.example.assignment03.service;

import com.example.assignment03.entity.DoctorUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDoctorUserService {
    Page<DoctorUser> findSpecializationsByName(Pageable pageable, String search);
}
