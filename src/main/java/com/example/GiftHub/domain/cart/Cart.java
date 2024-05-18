package com.example.GiftHub.domain.cart;

import com.example.GiftHub.domain.user.User;
import com.example.GiftHub.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "carrinho")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrinho")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Product product;

    @Column(name = "quantidade")
    private Integer quantity;

    public Cart(User user, Product product, Integer quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }

}
