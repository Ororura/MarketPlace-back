package com.ororura.audiomarket.services;

import com.ororura.audiomarket.entities.Image;
import com.ororura.audiomarket.repositories.ImageRepo;
import jakarta.transaction.Transactional;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Service
public class ImageService {
    private final ImageRepo imageRepo;

    @Autowired
    public ImageService(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    @Transactional
    public Resource getImage(String name, float scaleFloat) {
        try {
            Image image = imageRepo.findByName(name).get(0);
            InputStream is = new ByteArrayInputStream(image.getData());
            BufferedImage newBi = ImageIO.read(is);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Thumbnails.of(newBi)
                    .outputFormat("png")
                    .scale(scaleFloat)
                    .toOutputStream(baos);

            return new ByteArrayResource(baos.toByteArray());
        } catch (java.io.IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
