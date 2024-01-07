package com.example.assignment03.service.impl;

import com.example.assignment03.dto.ISpecializationsDTO;
import com.example.assignment03.dto.SpecializationsDTO;
import com.example.assignment03.entity.DoctorUser;
import com.example.assignment03.repository.IDoctorUserRepository;
import com.example.assignment03.repository.ISpecializationsRepository;
import com.example.assignment03.service.ISpecializationsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecializationsService implements ISpecializationsService {
    @Autowired
    private ISpecializationsRepository specializationsRepository;

    @Autowired
    private IDoctorUserRepository doctorUserRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ISpecializationsDTO> getTopSpecializations(){
     return specializationsRepository.getSpecializations();
    }

    @Override
    public List<SpecializationsDTO> searchBySpecializationsName(String search){
        // Lấy ra danh sách 1 List Doctor User
        List<DoctorUser> doctorUsers = doctorUserRepository.findBySpecializationsNameLike("%"+search+"%");
        // Convert Entities -> DTO
        List<SpecializationsDTO> dtos = doctorUsers.stream().map(i -> asDTO(i)).collect(Collectors.toList());
        return dtos;
    }

    public SpecializationsDTO asDTO(DoctorUser doctorUser){
        SpecializationsDTO dto =new SpecializationsDTO();
        dto.setSpecializationsName(doctorUser.getSpecializations().getName());
        dto.setDoctorName(doctorUser.getDoctor().getName());
        dto.setClinicsName(doctorUser.getClinics().getName());
        return dto;
    }





}
