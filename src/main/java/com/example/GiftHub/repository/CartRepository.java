package com.example.GiftHub.repository;

import com.example.GiftHub.domain.cart.Cart;


import com.example.GiftHub.domain.user.User;
import com.example.GiftHub.domain.product.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);
    Optional<Cart> findByUserAndProduct(User user, Product product);

    @Transactional
    @Modifying
    @Query("DELETE FROM Cart c WHERE c.user.userId = :userId")
    int deleteByUserId(@Param("userId") Long userId);
}
