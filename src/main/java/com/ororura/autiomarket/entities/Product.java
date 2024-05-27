package com.ororura.autiomarket.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "category")
    private String category;

    @JoinColumn(name = "image_id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Image image;

    @Column(name = "rate")
    private double rate;

    public Product() {}

    public Product(Long id, String title, double price, String description, String category, Image image, double rate, double ratingCount) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
        this.rate = rate;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double ratingRate) {
        this.rate = ratingRate;
    }


    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}