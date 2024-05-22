package com.example.GiftHub.domain.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long productId;

    @Column(name = "nome")
    private String name;

    @Column(name = "descricao")
    private String description;

    @Column(name = "preco")
    private BigDecimal price;

    @Column(name = "estoque")
    private int stock;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private ProductType productType;

    public Product(ProductDTO dto){
        this.name = dto.name();
        this.description = dto.description();
        this.price = dto.price();
        this.stock = dto.stock();
        this.productType = dto.productType();
    }
}

