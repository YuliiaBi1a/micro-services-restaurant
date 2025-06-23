package com.yuliia.product.service;

import com.yuliia.product.dto.ProductDto;
import com.yuliia.product.entity.Product;
import com.yuliia.product.mapping.ProductMapper;
import com.yuliia.product.repository.ProductRepository;
import com.yuliia.product.repository.SortProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SortProductRepository sortProductRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, SortProductRepository sortProductRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.sortProductRepository = sortProductRepository;
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
}
