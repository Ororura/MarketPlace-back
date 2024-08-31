package com.ororura.audiomarket.services;

import com.ororura.audiomarket.dtos.JwtResponse;
import com.ororura.audiomarket.dtos.SignInDTO;
import com.ororura.audiomarket.dtos.SignUpDTO;
import com.ororura.audiomarket.entities.user.Role;
import com.ororura.audiomarket.entities.user.UserEntity;
import com.ororura.audiomarket.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public JwtResponse signUp(SignUpDTO request) {
        UserDetails user = buildUser(request);
        UserEntity newUser = setUpNewUser(request);
        userService.createUser(newUser);
        return generateToken(user);
    }

    public JwtResponse signIn(SignInDTO signInDTO) {
        UserDetails user = userService.loadUserByUsername(signInDTO.getUsername());
        return generateToken(user);
    }

    public UserEntity setUpNewUser(SignUpDTO r) {
        UserEntity userEntity = new UserEntity();
        userEntity.setRole(Role.ROLE_USER);
        userEntity.setUsername(r.getUsername());
        userEntity.setPassword(passwordEncoder.encode(r.getPassword()));
        return userEntity;
    }

    public UserDetails buildUser(SignUpDTO r) {
        return User.builder()
                .username(r.getUsername())
                .roles("user")
                .password(passwordEncoder.encode(r.getPassword()))
                .build();
    }

    public JwtResponse generateToken(UserDetails u) {
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setResponse(jwtUtils.generateTokenFromUsername(u));
        return jwtResponse;
    }
}
