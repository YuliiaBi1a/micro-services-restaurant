package com.yuliia.product.controller;

import com.yuliia.product.dto.ProductDto;
import com.yuliia.product.service.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Slf4j
//@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService; // también podemos usar @RequiredArgsConstructor de Lombok
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductDto> getAllProducts(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        log.info("Calling getAllProducts");
        return productService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getProductsById(@PathVariable Long id) {
        log.info("Calling getProductsById with ID: {}", id);
        return productService.findById(id).orElseThrow();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto addProduct(@RequestBody ProductDto product) {
        log.info("Calling addProduct: {}", product);
        return productService.add(product);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductDto updateProduct(@RequestBody ProductDto product) {
        log.info("Calling updateProduct: {}", product);
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        log.info("Calling deleteProduct with ID: {}", id);
        productService.delete(id);
    }

}