package com.example.GiftHub.service;

import com.example.GiftHub.domain.cart.Cart;
import com.example.GiftHub.domain.cart.CartDTO;
import com.example.GiftHub.domain.user.User;
import com.example.GiftHub.domain.product.Product;
import com.example.GiftHub.repository.CartRepository;
import com.example.GiftHub.repository.ProductRepository;
import com.example.GiftHub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public List<CartDTO> getCartByUserId(Long id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("Cliente não encontrado com ID: " + id));

        List<CartDTO> cartDTOs = new ArrayList<>();
        for (Cart cart : user.getCarts()) {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setUserName(user.getFullName());
            cartDTO.setProductName(cart.getProduct().getName());
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
        cartDTO.setUserName(user.getFullName());
        cartDTO.setProductName(product.getName());
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
        cartDTO.setUserName(cart.getUser().getFullName());
        cartDTO.setProductName(cart.getProduct().getName());
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
}


