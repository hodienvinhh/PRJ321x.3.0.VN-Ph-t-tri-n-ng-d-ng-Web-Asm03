package com.example.assignment03.controller;

import com.example.assignment03.dto.PatientForDoctorDTO;
import com.example.assignment03.dto.PatientsDTO;
import com.example.assignment03.form.CreatePostForm;
import com.example.assignment03.service.IEmailService;
import com.example.assignment03.service.IPatientsService;
import com.example.assignment03.service.IPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "api/v1/doctor")
public class DoctorController {

    @Autowired
    private IEmailService emailService;


    @Autowired
    private IPostsService postsService;

    @Autowired
    private IPatientsService patientsService;

    // Đăng ký Posts
    @PostMapping("/create-Post")
    public ResponseEntity<String> createPosts(@RequestBody CreatePostForm form) throws ParseException {
        postsService.createPost(form);
        return ResponseEntity.ok("Đặt lịch thành công. Vui lòng đợi xác nhận từ bác sĩ .");
    }


    // Nhận/hủy lịch khám của bệnh nhân
    @PostMapping("/changeConfirmByDoctor")
    @PreAuthorize("hasAnyAuthority('Doctor')")
    public ResponseEntity<Void> changeConfirmByDoctor(@RequestParam int id, @RequestParam int status, @RequestParam String content) {
        postsService.changeConfirmByDoctor(id, status, content);
        return ResponseEntity.ok().build();
    }

    // Lấy ra danh sách bệnh nhân của 1 bác sĩ
    @GetMapping()
    @PreAuthorize("hasAnyAuthority('Doctor')")
    public Page<PatientForDoctorDTO> getAllPatient(Pageable pageable) {
            Page<PatientForDoctorDTO> patientDTOS = patientsService.getListPatientByDoctorId(pageable);
            return patientDTOS;

        }

}
