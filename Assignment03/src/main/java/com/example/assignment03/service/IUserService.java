package com.example.assignment03.service;

import com.example.assignment03.dto.Profile;
import com.example.assignment03.form.CreateUserForm;
import com.example.assignment03.form.FormChangePassword;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    void createUser(CreateUserForm form);

    void createDoctor(CreateUserForm form);

    void changePassword(String token, FormChangePassword changePassword);

    void lockOrUnlockUser(int id, String description, boolean isLock);

    Profile getProfile(int id);
}
