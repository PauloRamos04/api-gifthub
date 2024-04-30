package com.example.GiftHub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "endereco")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long enderecoId;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Customer customerId;

    @Column(name = "rua")
    private String road;

    @Column(name = "cidade")
    private String city;

    @Column(name = "estado")
    private String State;

    @Column(name = "cep")
    private String cep;

    @Column(name = "estado")
    private String country;
}