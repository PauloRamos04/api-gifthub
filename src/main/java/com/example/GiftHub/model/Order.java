package com.example.GiftHub.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "pedido")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
    private Product product;

    @Column(name = "dt_pedido")
    private Date dateOfOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido")
    private OrderStatus orderStatus;

    @Column(name = "quantidade")
    private Integer amount;
}
