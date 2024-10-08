package com.ororura.audiomarket.controllers;

import com.ororura.audiomarket.dtos.ProductDTO;
import com.ororura.audiomarket.entities.Product;
import com.ororura.audiomarket.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('user')")
    @PostMapping
    public ResponseEntity<Void> upload(@RequestPart(name = "product") Product product, @RequestPart(name = "file") MultipartFile file) {
        try {
            productService.saveProduct(product, file);
            productService.saveNotification(product);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasRole('user')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
