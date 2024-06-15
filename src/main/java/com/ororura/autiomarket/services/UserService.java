package com.ororura.autiomarket.services;

import com.ororura.autiomarket.entities.user.UserEntity;
import com.ororura.autiomarket.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void createUser(UserEntity userEntity) {
        this.userRepo.save(userEntity);
    }

    public UserDetails loadUserByUsername(String username) {
        return userRepo.findUserEntitiesByUsername(username);
    }

    public UserDetailsService userDetailsService() {
        return this::loadUserByUsername;
    }

    public UserDetails getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return loadUserByUsername(username);
    }
}
