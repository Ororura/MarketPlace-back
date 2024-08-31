package com.ororura.audiomarket.controllers;

import com.ororura.audiomarket.services.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("/{name}/{scaleFloat}")
    public ResponseEntity<Resource> getImage(@PathVariable String name, @PathVariable int scaleFloat) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        Resource resource = imageService.getImage(name, scaleFloat);
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
