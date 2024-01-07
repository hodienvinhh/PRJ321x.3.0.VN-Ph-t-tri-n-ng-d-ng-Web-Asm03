package com.example.assignment03.controller;


import com.example.assignment03.configure.JwtTokenUtil;
import com.example.assignment03.form.LoginForm;
import com.example.assignment03.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@Validated @RequestBody LoginForm request) throws Exception {

        authenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = userService
                .loadUserByUsername(request.getEmail());

        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok("Token :" + token);
    }

//    @GetMapping("hello")
////    @PreAuthorize("hasAnyAuthority('Doctor', 'Admin')")
//    public ResponseEntity<?> hello(){
//        int a = 1;
//        return new ResponseEntity<>("hello", HttpStatus.OK);
//    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
