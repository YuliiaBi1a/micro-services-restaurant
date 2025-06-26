package com.yuliia.product.service;

import com.yuliia.product.dto.ProductDto;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    public ProductDto add(ProductDto productDto);
    public Optional<ProductDto> findById(Long id);
    public Page<ProductDto> getAll(Pageable page);
    public ProductDto update(ProductDto product);
    public void delete(Long id);

    // para usar template
    public boolean getAvailability(String sku);

}
