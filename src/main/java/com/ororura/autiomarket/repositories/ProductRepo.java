package com.ororura.autiomarket.repositories;

import com.ororura.autiomarket.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
