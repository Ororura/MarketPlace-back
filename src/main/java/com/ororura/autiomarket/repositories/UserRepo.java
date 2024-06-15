package com.ororura.autiomarket.repositories;

import com.ororura.autiomarket.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findUserEntitiesByUsername(String username);

    boolean existsUserByUsername(String username);
}
