package com.example.GiftHub.controller;

import com.example.GiftHub.domain.cart.CartDTO;
import com.example.GiftHub.domain.payment.PaymentTransaction;
import com.example.GiftHub.domain.user.User;
import com.example.GiftHub.service.CartService;
import com.example.GiftHub.service.PaymentService;
import com.example.GiftHub.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);


    @GetMapping("/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId) {
        try {
            List<CartDTO> carts = cartService.getCartByUserId(userId);
            return new ResponseEntity<>(carts, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProductToCart(@RequestBody CartDTO cartDTO) {
        try {
            CartDTO cart = cartService.addProductToCart(cartDTO.getUserId(), cartDTO.getProductId(), cartDTO.getQuantity());
            return new ResponseEntity<>(cart, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{userId}/checkout")
    public ResponseEntity<?> checkoutCart(@PathVariable Long userId) {
        try {
            BigDecimal totalAmount = cartService.calculateTotalCartAmount(userId);
            if (totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
                return new ResponseEntity<>("Não há itens no carrinho", HttpStatus.BAD_REQUEST);
            }

            User user = userService.getUserById(userId);

            // Simula o pagamento apenas se houver itens no carrinho
            PaymentTransaction paymentTransaction = paymentService.simulatePayment(user, totalAmount);

            // Persistir a transação de pagamento
            paymentTransaction = paymentService.savePaymentTransaction(paymentTransaction);

            // Limpar o carrinho após o pagamento bem-sucedido
            cartService.clearCart(userId);

            return new ResponseEntity<>(paymentTransaction, HttpStatus.CREATED);
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
