package com.example.assignment03.service.impl;

import com.example.assignment03.dto.Profile;
import com.example.assignment03.dto.UserLoginDTO;
import com.example.assignment03.entity.Appointments;
import com.example.assignment03.entity.User;
import com.example.assignment03.exception.BasicException;
import com.example.assignment03.form.CreateUserForm;
import com.example.assignment03.form.FormChangePassword;
import com.example.assignment03.repository.IAppointmentsRepository;
import com.example.assignment03.repository.IRoleRepository;
import com.example.assignment03.repository.IUserRepository;
import com.example.assignment03.service.IUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IAppointmentsRepository appointmentsRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(){
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void createUser(CreateUserForm form) {
        // omit id field
        TypeMap<CreateUserForm, User> typeMap = modelMapper.getTypeMap(CreateUserForm.class, User.class);
        if (typeMap == null) { // if not already added
            // skip field
            modelMapper.addMappings(new PropertyMap<CreateUserForm, User>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        // convert form to entity
        User user = modelMapper.map(form, User.class);
          user.setPassword(bCryptPasswordEncoder.encode(form.getPassword()));
          user.setRole(roleRepository.findById(3).get());
          user.setCreateAt(new Date());
        userRepository.save(user);
    }


    @Override
    public void createDoctor(CreateUserForm form) {
        // omit id field
        TypeMap<CreateUserForm, User> typeMap = modelMapper.getTypeMap(CreateUserForm.class, User.class);
        if (typeMap == null) { // if not already added
            // skip field
            modelMapper.addMappings(new PropertyMap<CreateUserForm, User>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        // convert form to entity
        User user = modelMapper.map(form, User.class);
          user.setGeneralIntroduction(form.getGeneralIntroduction());
          user.setTrainingProcess(form.getTrainingProcess());
          user.setAchievementsAchieved(form.getAchievementsAchieved());
          user.setSpecialties(form.getSpecialties());
          user.setPassword(bCryptPasswordEncoder.encode(form.getPassword()));
          user.setRole(roleRepository.findById(2).get());
          user.setCreateAt(new Date());
        userRepository.save(user);
    }

    @Override
    public void changePassword(String token, FormChangePassword changePassword) {
        // Tìm user theo token
        User user = userRepository.findByToken(token);
        if (Objects.isNull(user)){
            throw new RuntimeException("Not Found User");
        }
        //set password  (encoder)
        user.setPassword(bCryptPasswordEncoder.encode(changePassword.getNewPassword()));
        // Xóa token trong bảng user
        user.setToken(null);
        // Lưu lại
        userRepository.save(user);
    }

    @Override
    public void lockOrUnlockUser(int id, String description, boolean isLock){
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if (optional.isPresent()){
            user = optional.get();
            boolean statusAcc = user.getIsActive()==0 ? true : false;
            if (statusAcc == isLock)
                throw new RuntimeException("");
            if (user.getIsActive() == 1) {
                user.setIsActive(0);
                user.setDescription(description);
            }else {
                user.setIsActive(1);
            }
                userRepository.save(user);
        }else {
            throw BasicException.NOT_FOUND.addError("Không tìm thấy ID : "+ id);
        }
    }

    @Override
    public Profile getProfile(int id) {
        // Add User
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            throw BasicException.NOT_FOUND.addError("Không tìm thấy ID : "+ id);
        }
        Profile.UserDTO userDTO = new Profile.UserDTO(optionalUser.get());

        // Add Schedules
        List<Appointments> appointments = appointmentsRepository.findAllByPatientsId(id);
        if (appointments.isEmpty()){
            throw BasicException.NOT_FOUND.addError("Không tìm thấy Appointments");
        }
        List<Profile.AppointmentsDTO> appointmentsDTOS = new ArrayList<>();
        for (Appointments app : appointments){
            Profile.AppointmentsDTO appointmentsDTO = new Profile.AppointmentsDTO(app);
            appointmentsDTOS.add(appointmentsDTO);
        }
        Profile profile = new Profile(userDTO, appointmentsDTOS);
        return profile;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("Not Found User");
        }
        return new UserLoginDTO(username, user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().getRoleName()), user);

    }
}
