package com.example.GiftHub.controller;

import com.example.GiftHub.domain.user.User;
import com.example.GiftHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("auth")
public class VerificationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam("token") UUID token) {
        User user = userRepository.findByVerificationToken(token);

        if (user == null) {
            return ResponseEntity.badRequest().body("Token inválido.");
        }

        user.setVerified(true);
        user.setVerificationToken(null);
        userRepository.save(user);

        return ResponseEntity.ok("Usuário verificado com sucesso.");
    }
}