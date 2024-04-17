package com.patientproject.controller;


import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patientproject.auth.AuthenticationRequest;
import com.patientproject.auth.AuthenticationResponse;
import com.patientproject.auth.RegisterRequest;
import com.patientproject.service.AuthenticationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) {
        try {
            String successMessage = authenticationService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody AuthenticationRequest request) {
        try {
            String result = authenticationService.authenticate(request);
            if (result.startsWith("Authentication successful")) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred. Please try again later.");
        }
    }
}
