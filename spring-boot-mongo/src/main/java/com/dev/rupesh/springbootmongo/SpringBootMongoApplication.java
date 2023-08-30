package com.dev.rupesh.springbootmongo;

import com.dev.rupesh.springbootmongo.product.model.Product;
import com.dev.rupesh.springbootmongo.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootMongoApplication {

    private final ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongoApplication.class, args);
    }

    @PostConstruct
    public void init() {
        System.out.println("Application started...");
        Product product = new Product();
        product.setName("Product 1");
        productRepository.save(product);
    }
}
