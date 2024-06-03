package com.ororura.autiomarket.services;

import com.ororura.autiomarket.repositories.ImageRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {


    private final ImageRepo imageRepo;

    @Autowired
    public ImageService(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }


    @Transactional
    public byte[] getImage(String name) {
        return this.imageRepo.findByName(name).get(0).getData();
    }

}
