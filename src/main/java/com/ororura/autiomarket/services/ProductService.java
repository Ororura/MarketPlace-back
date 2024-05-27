package com.ororura.autiomarket.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ororura.autiomarket.entities.Image;
import com.ororura.autiomarket.entities.Product;
import com.ororura.autiomarket.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductService(ProductRepo productRepo, ObjectMapper objectMapper) {
        this.productRepo = productRepo;
        this.objectMapper = objectMapper;
    }

    public void saveProduct(String productJson, MultipartFile file) throws IOException {
        Product product = objectMapper.readValue(productJson, Product.class);
        product.setImage(fileToImage(file));
        productRepo.save(product);
    }

    public Image fileToImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setData(file.getBytes());
        image.setName(file.getName());
        return image;
    }


}
