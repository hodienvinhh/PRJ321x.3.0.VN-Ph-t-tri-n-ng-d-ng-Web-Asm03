package com.example.assignment03.dto;

import com.example.assignment03.entity.Appointments;
import com.example.assignment03.entity.Schedules;
import com.example.assignment03.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class Profile {

    private UserDTO userDTO;
    private List<AppointmentsDTO> schedulesDTOS;


    public Profile(UserDTO userDTO, List<AppointmentsDTO> schedulesDTOS) {
        this.userDTO = userDTO;
        this.schedulesDTOS = schedulesDTOS;
    }

    public Profile() {
    }

    @Data
    public static class UserDTO{
        private int id;
        private String name;
        private String gender;
        private String email;
        private String phone;
        private String address;

        public UserDTO(User user){
            this.id = user.getId();
            this.name = user.getName();
            this.gender = user.getGender();
            this.email = user.getEmail();
            this.phone = user.getPhone();
            this.address = user.getAddress();
        }
    }

    @Data
    public static class AppointmentsDTO{

        private String patientsName;
        private String placesName;
        private String pathological;
        private String createAt;

        public AppointmentsDTO(Appointments appointments){
            this.patientsName = appointments.getPatients().getName();
            this.placesName = appointments.getPlaces().getNamePalaces();
            this.pathological = appointments.getPathological();
            this.createAt = String.valueOf(appointments.getCreateAt());
        }
    }


}
