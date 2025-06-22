package com.yuliia.product.dto;

public record ProductDto(
        Long id,
        String sku,
        String name,
        String description,
        Double price
) {
}
