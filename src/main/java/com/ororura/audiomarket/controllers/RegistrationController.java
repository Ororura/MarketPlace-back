package com.ororura.audiomarket.controllers;

import com.ororura.audiomarket.dtos.JwtResponse;
import com.ororura.audiomarket.dtos.SignUpDTO;
import com.ororura.audiomarket.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public JwtResponse registration(@RequestBody SignUpDTO signUpDTO) {
        return this.authenticationService.signUp(signUpDTO);
    }
}
