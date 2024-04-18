package com.patientproject.service;





import org.hibernate.service.spi.ServiceException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.patientproject.auth.AuthenticationRequest;
import com.patientproject.auth.RegisterRequest;
import com.patientproject.entity.User;
import com.patientproject.enumeration.UserType;
import com.patientproject.repo.UserRepository;
import com.patientproject.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest request) {
        try {
            User user = User.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userType(UserType.ADMIN)
                .build();

            userRepository.save(user);
            return "Registration successful";
        } catch (Exception e) {
            throw new ServiceException("An error occurred while processing your registration request. Please try again later.", e);
        }
    }

    public String authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + request.getEmail()));
            String jwtToken = jwtService.generateToken(user);
            return "Authentication successful: " + jwtToken;
        } catch (Exception e) {
            return "Authentication failed: " + e.getMessage();
        }
    }
    }
    
//    public String authenticate(AuthenticationRequest request) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            request.getEmail(),
//                            request.getPassword()));
//        } catch (AuthenticationException e) {
//            throw new AuthenticationServiceException("Incorrect username or password");
//        }
//        var user = userRepository.findByUserName(request.getUserName()).orElseThrow();
//        return jwtService.generateToken(user);
//    }
