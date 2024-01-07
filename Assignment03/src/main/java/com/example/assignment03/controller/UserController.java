package com.example.assignment03.controller;

import com.example.assignment03.dto.*;
import com.example.assignment03.entity.Clinics;
import com.example.assignment03.form.CreateUserForm;
import com.example.assignment03.form.FormChangePassword;
import com.example.assignment03.service.*;
import com.example.assignment03.utils.ContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IClinicsService clinicsService;

    @Autowired
    private ISpecializationsService specializationsService;

    @Autowired
    private IDoctorUserService doctorUserService;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private ContextUtils contextUtils;

    // Đăng ký User
    @PostMapping("/create-User")
    public ResponseEntity<String> createUser(@RequestBody CreateUserForm form) {
        userService.createUser(form);
        return ResponseEntity.ok("Đăng ký thành công. Tên người dùng: " + form.getName());
    }

    // Hiển thị thông tin cá nhân
    @GetMapping("/show-InformationUser")
    public Profile showUserInformation(){
        Profile profile = userService.getProfile(contextUtils.getUserId());
        return profile;
    }

    // Request gửi đi để xác nhận email
    @PostMapping("/forgot-password")
    public ResponseEntity<String> confirmEmail(@RequestParam String email) {
        // Lấy mã xác nhận
        emailService.forgotPassword(email);

        // Trả về kết quả
        return ResponseEntity.ok("Đã gửi email xác nhận. Vui lòng kiểm tra email của bạn.");
    }

    // 2: Request gửi để xác thực lại mật khẩu tương ứng bao gồm (mật khẩu và nhập lại mật khẩu)
    @PostMapping("/changePassword")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestBody FormChangePassword changePassword) {
        userService.changePassword(token, changePassword);
        return ResponseEntity.ok("Thay đổi mật khẩu thành công !");
    }


    // Tìm kiếm chung
    @GetMapping("/clinics-Name")
    public Page<Clinics> getClinicForSearch(Pageable pageable,
                                            @RequestParam(value = "search", required = false) String search) {
        Page<Clinics> clinics = clinicsService.getClinicByName(pageable, search);
        return clinics;

    }

    // Tìm kiếm theo chuyên khoa của bác sĩ
    @GetMapping("/specialization-Name")
    public List<SpecializationsDTO> getSpecializationForSearch(Pageable pageable,
                                                               @RequestParam(value = "search", required = false) String search) {
        List<SpecializationsDTO> specializationsDTOList = specializationsService.searchBySpecializationsName(search);
        return specializationsDTOList;
    }

    // Hiển thị thông tin của các chuyên khoa nổi bật
    @GetMapping("top-Specializations")
//    @PreAuthorize("hasAnyAuthority('Admin')")
    private List<ISpecializationsDTO> getSpecializations(){
       return specializationsService.getTopSpecializations();
    }


    // Hiển thị thông tin của các cơ sở y tế nổi bật
    @GetMapping("top-Clinics")
    private List<IClinicsDTO> getTopClinics(){
        return clinicsService.getTopClinics();
    }


}

