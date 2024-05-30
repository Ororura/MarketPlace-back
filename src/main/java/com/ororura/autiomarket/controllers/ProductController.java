package com.ororura.autiomarket.controllers;

import com.ororura.autiomarket.entities.Notification;
import com.ororura.autiomarket.entities.Product;
import com.ororura.autiomarket.services.NotificationService;
import com.ororura.autiomarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final NotificationService notificationService;

    @Autowired
    public ProductController(ProductService productService, NotificationService notificationService) {
        this.productService = productService;
        this.notificationService = notificationService;
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK);
    }


    @MessageMapping("/test")
    @SendTo("/topic/test")
    public List<Notification> test() {
        return this.notificationService.findAllNotifications();
    }

    @PostMapping
    public ResponseEntity<List<Notification>> upload(@RequestPart(name = "product") Product product, @RequestPart(name = "file") MultipartFile file) {
        try {
            Notification notification = new Notification();
            notification.setProduct(product);
            notification.setStatus("created");
            productService.saveProduct(product, file);
            notificationService.saveNotifications(notification);
            return new ResponseEntity<>(this.notificationService.findAllNotifications(), HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
