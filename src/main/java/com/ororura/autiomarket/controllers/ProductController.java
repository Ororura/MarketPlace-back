package com.ororura.autiomarket.controllers;

import com.ororura.autiomarket.entities.Product;
import com.ororura.autiomarket.repositories.ProductRepo;
import com.ororura.autiomarket.services.ImageService;
import com.ororura.autiomarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductController {
    private final ImageService imageService;
    private final ProductRepo productRepo;
    private final ProductService productService;

    @Autowired
    public ProductController(ImageService imageService, ProductRepo productRepo, ProductService productService) {
        this.imageService = imageService;
        this.productRepo = productRepo;
        this.productService = productService;
    }


    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @PostMapping("/upload")
    public ResponseEntity<Object> upload(@RequestPart(name = "product") Product product, @RequestPart(name = "file") MultipartFile file) throws IOException {
        try {
            productService.saveProduct(product, file);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
