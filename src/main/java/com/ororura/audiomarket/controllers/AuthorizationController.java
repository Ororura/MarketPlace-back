package com.ororura.audiomarket.controllers;

import com.ororura.audiomarket.dtos.SignInDTO;
import com.ororura.audiomarket.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sign-in")
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthenticationService authenticationService;

    @PostMapping
    public String signIn(@RequestBody SignInDTO signInDTO) {
        return authenticationService.signIn(signInDTO);
    }
}
