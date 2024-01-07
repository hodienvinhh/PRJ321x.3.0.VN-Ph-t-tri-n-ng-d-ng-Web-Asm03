package com.example.assignment03.dto;

import com.example.assignment03.entity.Patients;
import com.example.assignment03.entity.Posts;
import lombok.Data;

import java.util.List;

@Data
public class PostsDTO {
    private PatientDTO patientDTO;
    private List<PostDTO> postDTOs;

    public PostsDTO(PatientDTO patientDTO, List<PostDTO> postDTOs) {
        this.patientDTO = patientDTO;
        this.postDTOs = postDTOs;
    }
    @Data
    public static class PatientDTO{
        private String name;

        public PatientDTO(Patients patients){
            this.name = patients.getName();
        }
    };

    @Data
    public static class PostDTO{
        private String title;
        private String doctorName;
        private String specializationsName;
        private String clinicsName;
        private String dateTimeBooking;
        private String createAt;

        // Convert entity --> DTO
    public PostDTO(Posts posts) {
        this.title = posts.getTitle();
        this.doctorName = posts.getDoctorId().getName();
        this.specializationsName = posts.getSpecializations().getName();
        this.clinicsName = posts.getClinics().getName();
        this.dateTimeBooking = String.valueOf(posts.getDateTimeBooking());
        this.createAt = String.valueOf(posts.getCreateAt());
    }
  }

}
