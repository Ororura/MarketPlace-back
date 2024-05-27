package com.ororura.autiomarket.services;

import com.ororura.autiomarket.entities.Image;
import com.ororura.autiomarket.repositories.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {


    private final ImageRepo imageRepo;

    @Autowired
    public ImageService(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    public void saveImage(MultipartFile imageFile) throws IOException {
        Image image = new Image();
        image.setData(imageFile.getBytes());
        image.setName(imageFile.getName());
        imageRepo.save(image);
    }

    public Image getImage(Long id) {
        return imageRepo.findById(id).orElse(null);
    }

}
