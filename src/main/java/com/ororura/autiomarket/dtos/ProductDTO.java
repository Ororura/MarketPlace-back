package com.ororura.autiomarket.dtos;

public record ProductDTO(long id, String title, double price, String description, String category, String imageName, double rate) {
}