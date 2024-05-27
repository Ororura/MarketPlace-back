package com.ororura.autiomarket.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ororura.autiomarket.repositories.ImageRepo;
import com.ororura.autiomarket.repositories.ProductRepo;
import com.ororura.autiomarket.services.ImageService;
import com.ororura.autiomarket.services.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class AppConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public ImageService image(ImageRepo imageRepo) {
        return new ImageService(imageRepo);
    }

    @Bean
    public ProductService productService(ProductRepo productRepo, ObjectMapper objectMapper) {
        return new ProductService(productRepo, objectMapper);
    }
}
