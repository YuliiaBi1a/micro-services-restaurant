package com.yuliia.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String sku;
    private String name;
    private String description;
    private Double price;
}
