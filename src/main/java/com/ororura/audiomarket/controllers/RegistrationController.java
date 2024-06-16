package com.ororura.audiomarket.controllers;

import com.ororura.audiomarket.dtos.SignUpDTO;
import com.ororura.audiomarket.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final AuthenticationService authenticationService;


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
    public String registration(@RequestBody SignUpDTO signUpDTO) {
        return this.authenticationService.signUp(signUpDTO);
    }
}
