package com.example.GiftHub.domain.address;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "endereco")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "endereco_id")
    private Long id;

    @NotNull
    @Column(name = "rua")
    private String street;

    @Column(name = "cidade")
    private String city;

    @Column(name = "estado")
    private String state;

    @Column(name = "cep")
    private String zipCode;

    @Column(name = "pais")
    private String country;

    // Construtor(es), getters e setters
}
