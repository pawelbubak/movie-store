package com.take.store.api.cart;

import com.take.store.model.cart.Cart;
import com.take.store.model.cart.CartItem;
import com.take.store.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public Cart getCartContent() {
        return cartService.getCartContent();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartItem addItemToCart(@RequestBody CartItem item) {
        return cartService.addItemToCart(item);
    }

    @PutMapping("{movieId}")
    public CartItem updateCartContent(@PathVariable long movieId, @RequestBody CartItem cartItem) {
        return cartService.updateCartContent(movieId, cartItem);
    }

    @DeleteMapping("{movieId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItemFromCart(@PathVariable long movieId) {
        cartService.deleteItemFromCart(movieId);
    }
}
