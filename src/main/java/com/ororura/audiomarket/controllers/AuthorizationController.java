package com.ororura.audiomarket.controllers;

import com.ororura.audiomarket.dtos.SignInDTO;
import com.ororura.audiomarket.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sign-in")
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthenticationService authenticationService;

    @GetMapping
    public String signIn() {
        SignInDTO signInDTO = new SignInDTO();
        signInDTO.setUsername("bbbbb");
        signInDTO.setPassword("kek");
        String token = authenticationService.signIn(signInDTO);
        System.out.println(token);
        return token;
    }
}
