package com.example.GiftHub.domain.user;


import com.example.GiftHub.domain.address.Address;

public record UserDTO(
        String fullName,
        String email,
        String login,
        String cpf,
        String dateOfBirth,
        String password,
        Address address,
        UserRole role
) {}
