package com.yuliia.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "A generic product") //para documentation
public record ProductDto(

        Long id,
        String sku,
        String name,
        String description,
        Double price
) {
}
