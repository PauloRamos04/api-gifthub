package com.example.GiftHub.domain.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {
    private Long userId;
    private Long productId;
    private Integer quantity;
}
