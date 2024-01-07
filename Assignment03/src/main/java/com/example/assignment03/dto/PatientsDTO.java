package com.example.assignment03.dto;

import com.example.assignment03.entity.Patients;
import com.example.assignment03.entity.Schedules;
import lombok.Data;

import java.util.List;

@Data
public class PatientsDTO {
    private ScheduleDTO scheduleDTO;
    private List<PatDTO> patDTOS;


    public PatientsDTO(ScheduleDTO scheduleDTO, List<PatDTO> patDTOS) {
        this.scheduleDTO = scheduleDTO;
        this.patDTOS = patDTOS;
    }

    public PatientsDTO() {
    }

    @Data
    public static class ScheduleDTO{
        private String doctorName;

        public ScheduleDTO(Schedules schedules){
            this.doctorName = schedules.getDoctorId().getName();
        }
    }


    @Data
    public static class PatDTO{
    private Integer id;
    private String name;
    private String gender;
    private String address;
    private String pathological;
    private String describe;

    public PatDTO(Patients patients){
        this.id = patients.getId();
        this.name = patients.getName();
        this.gender = patients.getGender();
        this.address = patients.getAddress();
        this.pathological = patients.getPathological();
        this.describe = patients.getDescribe();
    }
  }
}
