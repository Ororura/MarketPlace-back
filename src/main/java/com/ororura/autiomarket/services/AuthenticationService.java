package com.ororura.autiomarket.services;

import com.ororura.autiomarket.dtos.SignUpDTO;
import com.ororura.autiomarket.entities.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String signUp(SignUpDTO request) {

        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(String.valueOf(Role.ROLE_USER))
                .build();

        userService.createUser((com.ororura.autiomarket.entities.user.User) user);

        return jwtService.generateJwtToken(user);
    }
}
