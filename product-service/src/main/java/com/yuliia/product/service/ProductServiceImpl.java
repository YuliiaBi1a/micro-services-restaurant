package com.yuliia.product.service;

import com.yuliia.product.config.RestTemplateConfiguration;
import com.yuliia.product.dto.ProductDto;
import com.yuliia.product.entity.Product;
import com.yuliia.product.mapping.ProductMapper;
import com.yuliia.product.repository.ProductRepository;
import com.yuliia.product.repository.SortProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String INVENTORY_SERVICE_URL = "lb://"; // tengo que arreglar load balancer

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SortProductRepository sortProductRepository;
    private final RestTemplate restTemplate;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, SortProductRepository sortProductRepository, RestTemplateConfiguration restTemplate, RestTemplate restTemplate1) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.sortProductRepository = sortProductRepository;
        this.restTemplate = restTemplate1;
    }

    @Override
    public ProductDto add(ProductDto productDto) {
        Product productEntity = productMapper.toEntity(productDto);
        return productMapper.toDto(productRepository.save(productEntity));
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto);
    }
/*
    @Override
    public Page<ProductDto> getAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toDto);
    }*/

    @Override
    public Page<ProductDto> getAll(Pageable pageable) {
        return sortProductRepository.findAll(pageable)
                .map(productMapper::toDto);
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        var entity = productMapper.toEntity(productDto);
        var updated = productRepository.save(entity);
        return productMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean getAvailability(String sku) {
        ResponseEntity<Boolean> response = restTemplate.exchange(INVENTORY_SERVICE_URL + "/" + sku,
                HttpMethod.GET,
                null,
                Boolean.class);
        return false;
    }
}
