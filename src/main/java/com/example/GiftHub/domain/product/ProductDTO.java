package com.example.GiftHub.domain.product;

public record ProductDTO(
        String name,
        String descritption,
        Double price,
        Integer stock,
        ProductType productType
) {
}
