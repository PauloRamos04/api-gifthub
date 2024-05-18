package com.example.GiftHub.repository;

import com.example.GiftHub.domain.cart.Cart;


import com.example.GiftHub.domain.user.User;
import com.example.GiftHub.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserAndProduct(User user, Product product);
}
