package com.example.assignment03.service.impl;

import com.example.assignment03.dto.PatientForDoctorDTO;
import com.example.assignment03.dto.PatientsDTO;
import com.example.assignment03.entity.Patients;
import com.example.assignment03.repository.IPatientsRepository;
import com.example.assignment03.service.IPatientsService;
import com.example.assignment03.utils.ContextUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientsService implements IPatientsService {
    @Autowired
    private IPatientsRepository patientsRepository;

    @Autowired
    private ContextUtils contextUtils;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<PatientForDoctorDTO> getListPatientByDoctorId(Pageable pageable) {

        Page<Patients> pages = patientsRepository.findPatientsByDoctorId(pageable,contextUtils.getUserId());
        //findByUserId

        List<PatientForDoctorDTO> dtos = modelMapper.map(pages.getContent(), new TypeToken<List<PatientForDoctorDTO>>() {
        }.getType());

        Page<PatientForDoctorDTO> dtoPages = new PageImpl<>(dtos, pageable, pages.getTotalElements());

        return dtoPages;
    }



}
