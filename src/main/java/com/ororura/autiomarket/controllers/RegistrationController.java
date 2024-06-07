package com.ororura.autiomarket.controllers;

import com.ororura.autiomarket.dtos.SignUpDTO;
import com.ororura.autiomarket.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/hello")
    @PreAuthorize("hasRole('user')")
    public String hello() {
        return "Hello user!";
    }

    @GetMapping("/test")
    public String test() {
        SignUpDTO signUpDTO = new SignUpDTO();
        signUpDTO.setUsername("bbbbb");
        signUpDTO.setPassword("kek");
        String jwt = this.authenticationService.signUp(signUpDTO);

        System.out.println(jwt);
        return jwt;
    }

    @PostMapping
    public String registration(SignUpDTO signUpDTO) {
        return this.authenticationService.signUp(signUpDTO);
    }
}
