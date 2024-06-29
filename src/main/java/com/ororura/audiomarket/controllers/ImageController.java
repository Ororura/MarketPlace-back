package com.ororura.audiomarket.controllers;

import com.ororura.audiomarket.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{name}/{scaleFloat}")
    public ResponseEntity<Resource> getImage(@PathVariable String name, @PathVariable int scaleFloat) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        float scale = (float) scaleFloat / 100;

        Resource resource = imageService.getImage(name, scale);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }




}
