package com.ororura.autiomarket.repositories;

import com.ororura.autiomarket.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByUsername(String username);

    boolean existsUserByUsername(String username);
}
