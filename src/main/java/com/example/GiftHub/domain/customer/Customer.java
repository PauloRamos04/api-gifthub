package com.example.GiftHub.domain.customer;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long customerId;

    @NotNull(message = "O nome do cliente é obrigatorio")
    @Column(name = "nome_completo")
    private String fullName;

    @Column(name = "email")
    @NotNull(message = "Email obrigatorio")
    private String email;

    @Column(name = "cpf")
    @NotBlank(message = "Cpf obrigatorio")
    private String cpf;

    @Column(name = "dt_nasc")
    @NotNull(message = "Data obrigatoria")
    private String dateOfBirth;

    @Column(name = "senha")
    @NotNull(message = "Senha obrigatoria")
    private String password;

    public Customer(CustomerDTO dto) {
        this.fullName = dto.fullName();
        this.email = dto.email();
        this.cpf = dto.cpf();
        this.dateOfBirth = dto.dateOfBirth();
        this.password = dto.password();
    }
}

