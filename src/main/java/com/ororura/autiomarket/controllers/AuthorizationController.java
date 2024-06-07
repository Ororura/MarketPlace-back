package com.ororura.autiomarket.controllers;

import com.ororura.autiomarket.dtos.SignInDTO;
import com.ororura.autiomarket.dtos.SignUpDTO;
import com.ororura.autiomarket.services.AuthenticationService;
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
        signInDTO.setUsername("lol");
        signInDTO.setPassword("kek");
        String token = authenticationService.signIn(signInDTO);
        System.out.println(token);
        return token;
    }
}
