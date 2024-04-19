package com.example.GiftHub.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long customerId;

    @Column(name = "nome_completo")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "dt_nasc")
    private Date dateOfBirth;

    @Column(name = "senha")
    private String password;

}

