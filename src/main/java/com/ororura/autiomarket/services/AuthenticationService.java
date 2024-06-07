package com.ororura.autiomarket.services;

import com.ororura.autiomarket.dtos.SignInDTO;
import com.ororura.autiomarket.dtos.SignUpDTO;
import com.ororura.autiomarket.entities.user.Role;
import com.ororura.autiomarket.entities.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
                .roles("user")
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        UserEntity newUser = new UserEntity();
        newUser.setRole(Role.ROLE_USER);
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        userService.createUser(newUser);

        return jwtService.generateJwtToken(user);
    }

    public String signIn(SignInDTO signInDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDTO.getUsername(), signInDTO.getPassword()));
        UserDetails user = userService.userDetailsService().loadUserByUsername(signInDTO.getUsername());

        return jwtService.generateJwtToken(user);
    }
}
