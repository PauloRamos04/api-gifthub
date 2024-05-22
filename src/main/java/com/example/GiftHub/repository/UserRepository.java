package com.example.GiftHub.repository;


import com.example.GiftHub.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String email);
    User findByVerificationToken(UUID token);
}
