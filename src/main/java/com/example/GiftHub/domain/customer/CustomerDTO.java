package com.example.GiftHub.domain.customer;

public record CustomerDTO(
        String fullName,
        String email,
        String cpf,
        String dateOfBirth,
        String password

) {
}
