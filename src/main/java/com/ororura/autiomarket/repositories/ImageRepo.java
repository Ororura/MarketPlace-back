package com.ororura.autiomarket.repositories;

import com.ororura.autiomarket.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepo extends JpaRepository<Image, Long> {
    List<Image> findByName(String name);
}
