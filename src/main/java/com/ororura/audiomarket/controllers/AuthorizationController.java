package com.ororura.audiomarket.controllers;

import com.ororura.audiomarket.dtos.JwtResponse;
import com.ororura.audiomarket.dtos.SignInDTO;
import com.ororura.audiomarket.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
