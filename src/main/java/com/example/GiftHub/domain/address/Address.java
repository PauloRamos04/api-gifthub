package com.example.GiftHub.domain.address;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "endereco")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "rua")
    private String street;

    @NotNull
    @Column(name = "cidade")
    private String city;

    @NotNull
    @Column(name = "estado")
    private String state;

    @NotNull
    @Column(name = "cep")
    private String zipCode;

    @NotNull
    @Column(name = "pais")
    private String country;

    // Construtor padrão
    public Address() {
    }

    // Construtor com parâmetros
    public Address(String street, String city, String state, String zipCode, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }
}
