package com.ororura.autiomarket.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

}