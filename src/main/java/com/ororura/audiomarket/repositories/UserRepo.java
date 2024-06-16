package com.ororura.audiomarket.repositories;

import com.ororura.audiomarket.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findUserEntitiesByUsername(String username);

    boolean existsUserByUsername(String username);
}
