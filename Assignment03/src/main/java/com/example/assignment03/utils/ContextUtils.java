package com.example.assignment03.utils;

import com.example.assignment03.dto.UserLoginDTO;
import com.example.assignment03.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Service
public class ContextUtils {

   @Autowired
   private IUserService userService;


    public Integer getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            // Handle cases where authentication is not available
            return null;
        }
        UserLoginDTO userLoginDTO1 = (UserLoginDTO) authentication.getPrincipal();
        String email = userLoginDTO1.getEmail();

        if (email.equals("anonymousUser")) {
            return null;
        } else {
            try {
                UserLoginDTO userLoginDTO = (UserLoginDTO) userService.loadUserByUsername(email);
                if (userLoginDTO != null) {
                    return userLoginDTO.getId();
                } else {
                    // Handle cases where userDetails is not found
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
//    public Integer getUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication.getPrincipal().toString().equals("anonymousUser"))
//            return null;
//        else {
//            String email = authentication.getPrincipal().toString();
//            UserLoginDTO userDetails = (UserLoginDTO) userService.loadUserByUsername(email);
//            return userDetails.getId();
//        }
//    }
}
