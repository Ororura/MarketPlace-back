package com.ororura.audiomarket.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String imageName;
    private double rate;
}
