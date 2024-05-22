package com.example.GiftHub.domain.user;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(
        @NotBlank(message = "Login obrigatorio")
        String login,

        @NotBlank(message = "Senha obrigatoria")
        String password) {
}
