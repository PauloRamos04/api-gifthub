package com.example.GiftHub.domain.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {
    private String userName;
    private String productName;
    private Integer quantity;
}