package com.ororura.audiomarket.controllers;

import com.ororura.audiomarket.dtos.JwtResponse;
import com.ororura.audiomarket.dtos.SignInDTO;
import com.ororura.audiomarket.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sign-in")
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<JwtResponse> signIn(@RequestBody SignInDTO signInDTO) {
        return new ResponseEntity<>(authenticationService.signIn(signInDTO), HttpStatus.OK);
    }
}
