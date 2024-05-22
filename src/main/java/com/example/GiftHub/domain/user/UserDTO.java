package com.example.GiftHub.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.example.GiftHub.domain.address.Address;

public record UserDTO(
        @NotBlank(message = "O nome completo é obrigatório")
        String fullName,

        @Email(message = "Email deve ser válido")
        @NotBlank(message = "Email é obrigatório")
        String email,

        @NotBlank(message = "Login é obrigatório")
        String login,

        @NotBlank(message = "CPF é obrigatório")
        String cpf,

        @NotBlank(message = "Data de nascimento é obrigatória")
        String dateOfBirth,

        @NotBlank(message = "Senha é obrigatória")
        String password,

        @NotNull(message = "Endereço é obrigatório")
        Address address,

        UserRole role
) {}
