package com.take.store.model.cart;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Cart {
    private List<CartItem> items;
    private double totalPrice;
}
