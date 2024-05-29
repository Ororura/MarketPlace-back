package com.ororura.autiomarket.services;

import com.ororura.autiomarket.entities.Image;
import com.ororura.autiomarket.repositories.ImageRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class ImageService {


    private final ImageRepo imageRepo;

    @Autowired
    public ImageService(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    @Transactional
    public void saveImage(MultipartFile imageFile) throws IOException {
        Image image = new Image();
        image.setData(imageFile.getBytes());
        image.setName(imageFile.getOriginalFilename());
        imageRepo.save(image);
    }

    @Transactional
    public byte[] getImage(String name) {
        return this.imageRepo.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found")).getData();
    }

}
