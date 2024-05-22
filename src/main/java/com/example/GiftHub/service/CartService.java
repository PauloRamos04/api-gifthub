package com.example.GiftHub.service;

import com.example.GiftHub.domain.cart.Cart;
import com.example.GiftHub.domain.cart.CartDTO;
import com.example.GiftHub.domain.user.User;
import com.example.GiftHub.domain.product.Product;
import com.example.GiftHub.repository.CartRepository;
import com.example.GiftHub.repository.ProductRepository;
import com.example.GiftHub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);


    public List<CartDTO> getCartByUserId(Long userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Cliente não encontrado com ID: " + userId));

        List<CartDTO> cartDTOs = new ArrayList<>();
        for (Cart cart : user.getCarts()) {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setUserId(user.getUserId());
            cartDTO.setProductId(cart.getProduct().getProductId());
            cartDTO.setQuantity(cart.getQuantity());
            cartDTOs.add(cartDTO);
        }
        return cartDTOs;
    }


    @Transactional
    public CartDTO addProductToCart(Long userId, Long productId, int quantity) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Cliente não encontrado com ID: " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception("Produto não encontrado com ID: " + productId));

        Cart cart = cartRepository.findByUserAndProduct(user, product)
                .orElse(new Cart(user, product, 0));

        cart.setQuantity(cart.getQuantity() + quantity);

        cartRepository.save(cart);

        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserId(user.getUserId());
        cartDTO.setProductId(product.getProductId());
        cartDTO.setQuantity(cart.getQuantity());

        return cartDTO;
    }

    @Transactional
    public CartDTO updateProductQuantityInCart(Long userId, Long productId, int quantity) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Cliente não encontrado com ID: " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception("Produto não encontrado com ID: " + productId));

        Cart cart = cartRepository.findByUserAndProduct(user, product)
                .orElseThrow(() -> new Exception("Produto não encontrado no carrinho do cliente"));

        cart.setQuantity(quantity);
        cartRepository.save(cart);

        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserId(cart.getUser().getUserId());
        cartDTO.setProductId(cart.getProduct().getProductId());
        cartDTO.setQuantity(cart.getQuantity());

        return cartDTO;
    }

    @Transactional
    public void removeProductFromCart(Long userId, Long productId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Cliente não encontrado com ID: " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception("Produto não encontrado com ID: " + productId));

        Cart cart = cartRepository.findByUserAndProduct(user, product)
                .orElseThrow(() -> new Exception("Produto não encontrado no carrinho do cliente"));

        cartRepository.delete(cart);
    }

    public BigDecimal calculateTotalCartAmount(Long userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Cliente não encontrado com ID: " + userId));

        List<Cart> carts = user.getCarts();
        BigDecimal total = BigDecimal.ZERO;

        if (carts.isEmpty()) {
            return total;
        }

        for (Cart cart : carts) {
            BigDecimal productPrice = cart.getProduct().getPrice();
            total = total.add(productPrice.multiply(BigDecimal.valueOf(cart.getQuantity())));
        }

        return total;
    }


    @Transactional
    public void clearCart(Long userId) throws Exception {
        logger.info("Chamando o método clearCart para o usuário com ID: {}", userId);

        // Exclui os itens do carrinho com base no ID do usuário
        int deletedItems = cartRepository.deleteByUserId(userId);
        logger.info("Número de itens do carrinho excluídos para o usuário com ID {}: {}", userId, deletedItems);
    }




}
