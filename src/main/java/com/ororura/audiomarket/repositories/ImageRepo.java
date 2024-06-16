package com.ororura.audiomarket.repositories;

import com.ororura.audiomarket.entities.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
    List<Image> findByName(String name);
}
