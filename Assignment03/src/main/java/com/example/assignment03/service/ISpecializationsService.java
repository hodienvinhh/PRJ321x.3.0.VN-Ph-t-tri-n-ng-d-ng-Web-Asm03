package com.example.assignment03.service;

import com.example.assignment03.dto.ISpecializationsDTO;
import com.example.assignment03.dto.SpecializationsDTO;
import com.example.assignment03.entity.DoctorUser;
import com.example.assignment03.entity.Specializations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISpecializationsService {
    List<ISpecializationsDTO> getTopSpecializations();

    List<SpecializationsDTO> searchBySpecializationsName(String search);

}
