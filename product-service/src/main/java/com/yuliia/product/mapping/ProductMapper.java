package com.yuliia.product.mapping;

import com.yuliia.product.dto.ProductDto;
import com.yuliia.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDto dto);
    ProductDto toDto(Product entity);
}
