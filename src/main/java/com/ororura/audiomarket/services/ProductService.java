package com.ororura.audiomarket.services;

import com.ororura.audiomarket.dtos.ProductDTO;
import com.ororura.audiomarket.entities.Image;
import com.ororura.audiomarket.entities.Notification;
import com.ororura.audiomarket.entities.Product;
import com.ororura.audiomarket.repositories.ProductRepo;
import com.ororura.audiomarket.services.notification.NotificationService;
import com.ororura.audiomarket.services.notification.NotificationStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final NotificationService notificationService;

    public ProductService(ProductRepo productRepo, NotificationService notificationService) {
        this.productRepo = productRepo;
        this.notificationService = notificationService;
    }

    public List<ProductDTO> getAllProducts() {
        return convertToDTO(this.productRepo.findAll());
    }

    public void saveProduct(Product product, MultipartFile file) throws IOException {
        product.setImage(convertFileToImage(file));
        productRepo.save(product);
    }

    public void setNotification(Product product) {
        Notification notification = new Notification();
        notification.setProduct(product);
        notification.setStatus(NotificationStatus.CREATED);
        notificationService.saveNotifications(notification);
    }

    public void deleteProduct(Long id) {
        this.productRepo.deleteById(id);
    }

    public Image convertFileToImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setData(file.getBytes());
        image.setName(file.getOriginalFilename());
        return image;
    }

    public List<ProductDTO> convertToDTO(List<Product> products) {
        return products.stream().map(product -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setDescription(product.getDescription());
            productDTO.setTitle(product.getTitle());
            productDTO.setCategory(product.getCategory());
            productDTO.setPrice(product.getPrice());

            if (product.getImage() != null) {
                productDTO.setImageName(product.getImage().getName());
            }

            productDTO.setRate(product.getRate());
            return productDTO;
        }).collect(Collectors.toList());
    }

}
