package com.yuliia.product.repository;

import com.yuliia.product.dto.ProductDto;
import com.yuliia.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
