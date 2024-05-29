package com.ororura.autiomarket.services;

import com.ororura.autiomarket.entities.Image;
import com.ororura.autiomarket.entities.Product;
import com.ororura.autiomarket.repositories.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Transactional
    public List<Product> getAllProducts() {
        return this.productRepo.findAll();
    }

    @Transactional
    public void saveProduct(Product product, MultipartFile file) throws IOException {
        product.setImage(fileToImage(file));
        productRepo.save(product);
    }

    @Transactional
    public Image fileToImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setData(file.getBytes());
        image.setName(file.getOriginalFilename());
        return image;
    }


}
