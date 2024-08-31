package com.ororura.audiomarket.services;

import com.ororura.audiomarket.entities.Image;
import com.ororura.audiomarket.repositories.ImageRepo;
import net.coobird.thumbnailator.Thumbnails;
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

    public ImageService(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    public Resource getImage(String name, float scaleFloat) {
        Image image = imageRepo.findByName(name).get(0);
        InputStream inputStream = new ByteArrayInputStream(image.getData());
        BufferedImage bufferedImage = readImage(inputStream);
        return new ByteArrayResource(scaleImage(bufferedImage, scaleFloat / 100).toByteArray());
    }

    public BufferedImage readImage(InputStream inputStream) {
        try {
            return ImageIO.read(inputStream);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public ByteArrayOutputStream scaleImage(BufferedImage image, float scaleFloat) {
        try {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            Thumbnails.of(image)
                    .outputFormat("png")
                    .scale(scaleFloat)
                    .toOutputStream(byteArray);
            return byteArray;
        } catch (java.io.IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
