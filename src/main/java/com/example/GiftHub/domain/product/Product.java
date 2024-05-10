package com.example.GiftHub.domain.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produto")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long productId;

    @Column(name = "nome")
    @NotNull(message = "Nome obrigatorio")
    private String name;

    @Column(name = "descricao")
    private String description;

    @Column(name = "preco")
    @NotNull(message = "Preço obrigatorio")
    private double price;

    @Column(name = "estoque")
    @NotNull(message = "Estoque obrigatorio")
    private int stock;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Categoria obrigatorio")
    @Column(name = "categoria")
    private ProductType productType;

    public Product(ProductDTO dto){
        this.name = dto.name();
        this.description = dto.descritption();
        this.price = dto.price();
        this.stock = dto.stock();
        this.productType = dto.productType();
    }
}

