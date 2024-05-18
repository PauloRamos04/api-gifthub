package com.example.GiftHub.controller;

import com.example.GiftHub.domain.cart.Cart;
import com.example.GiftHub.domain.cart.CartDTO;
import com.example.GiftHub.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId) {
        try {
            List<CartDTO> carts = cartService.getCartByUserId(userId);
            return new ResponseEntity<>(carts, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<?> addProductToCart(@PathVariable Long userId, @PathVariable Long productId, @RequestParam int quantity) {
        try {
            CartDTO cart = cartService.addProductToCart(userId, productId, quantity);
            return new ResponseEntity<>(cart, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userId}/{productId}")
    public ResponseEntity<?> updateProductQuantityInCart(@PathVariable Long userId, @PathVariable Long productId, @RequestParam int quantity) {
        try {
            CartDTO cart = cartService.updateProductQuantityInCart(userId, productId, quantity);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<?> removeProductFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        try {
            cartService.removeProductFromCart(userId, productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
