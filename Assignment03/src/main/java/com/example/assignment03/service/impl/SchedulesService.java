package com.example.assignment03.service.impl;

import com.example.assignment03.dto.PatientsDTO;
import com.example.assignment03.entity.Patients;
import com.example.assignment03.entity.Schedules;
import com.example.assignment03.repository.IPatientsRepository;
import com.example.assignment03.repository.ISchedulesRepository;
import com.example.assignment03.service.ISchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchedulesService implements ISchedulesService {
    @Autowired
    private ISchedulesRepository schedulesRepository;

    @Autowired
    private IPatientsRepository patientsRepository;

    @Override
    public PatientsDTO viewAppointmentsDoctorByAdmin(int id){
        Optional<Schedules> schedulesOptional = schedulesRepository.findById(id);
        if (!schedulesOptional.isPresent()){
            throw  new RuntimeException("Not Found ID :" + id);
        }
        PatientsDTO.ScheduleDTO scheduleDTO = new PatientsDTO.ScheduleDTO(schedulesOptional.get());
        List<Patients> patients = patientsRepository.findAllByDoctorId(schedulesOptional.get().getId());
        if (patients.isEmpty()){
            throw new RuntimeException("Not Found ID :" + schedulesOptional.get());
        }
        List<PatientsDTO.PatDTO> patDTOS = new ArrayList<>();
        for (Patients pa : patients){
            PatientsDTO.PatDTO patDTO = new PatientsDTO.PatDTO(pa);
            patDTOS.add(patDTO);
        }
        PatientsDTO patientsDTO = new PatientsDTO(scheduleDTO, patDTOS);
        return patientsDTO;
    }

}
