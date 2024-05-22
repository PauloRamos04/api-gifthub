package com.example.GiftHub.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductDTO(
        @NotBlank(message = "Nome é obrigatório")
        String name,

        String description,

        @NotNull(message = "Preço é obrigatório")
        @PositiveOrZero(message = "Preço deve ser maior ou igual a zero")
        BigDecimal price,

        @NotNull(message = "Estoque é obrigatório")
        @PositiveOrZero(message = "Estoque deve ser maior ou igual a zero")
        Integer stock,

        @NotNull(message = "Tipo de produto é obrigatório")
        ProductType productType
) {
}
