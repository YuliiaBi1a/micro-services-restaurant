package com.yuliia.product.controller;

import com.yuliia.product.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getAllProducts() {
        log.info("Calling getAllProducts");
        return new ArrayList<>();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto addProduct(@RequestBody ProductDto product) {
        log.info("Calling addProduct: {}", product);
        return new ProductDto(1l, "1", "Product 1", "Product Descrption 1", 15.0);
    }

}