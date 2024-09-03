package com.ororura.audiomarket.services;

import com.ororura.audiomarket.entities.user.UserEntity;
import com.ororura.audiomarket.repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    public void createUser(UserEntity userEntity) {
        this.userRepo.save(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = userRepo.findUserEntitiesByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return user;
    }

}
