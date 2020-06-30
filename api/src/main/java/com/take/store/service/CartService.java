package com.take.store.service;

import com.take.store.model.cart.Cart;
import com.take.store.model.cart.CartItem;
import com.take.store.model.cart.CartItemId;
import com.take.store.model.exception.CartItemNotFoundException;
import com.take.store.model.user.User;
import com.take.store.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartItemRepository cartItemRepository;

    public Cart getCartContent() {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getCartContentByUserId(userDetails.getId());
    }

    private Cart getCartContentByUserId(long userId) {
        List<CartItem> items = cartItemRepository.findAllByIdUserId(userId);
        return Cart.builder()
                .items(items)
                .totalPrice(items.stream().reduce(0.0,
                        (sum, item) -> item.getQuantity() * item.getMovie().getPrice() + sum, Double::sum))
                .build();
    }

    public CartItem addItemToCart(CartItem item) {
        if (item == null || item.getMovie() == null) {
            throw new IllegalStateException("The information about item is invalid.");
        }
        CartItemId itemId = prepareCartItemIdForMovieId(item.getMovie().getId());
        return cartItemRepository.save(
                CartItem.builder()
                        .id(itemId)
                        .quantity(item.getQuantity())
                        .build());
    }

    public CartItem updateCartContent(long movieId, CartItem item) {
        if (item == null || item.getMovie() == null) {
            throw new IllegalStateException("The information about item is invalid.");
        }
        CartItemId itemId = prepareCartItemIdForMovieId(movieId);
        CartItem itemToUpdate = getCartItemById(itemId);
        itemToUpdate.setQuantity(item.getQuantity());
        return cartItemRepository.save(itemToUpdate);
    }

    public void deleteItemFromCart(long movieId) {
        CartItemId itemId = prepareCartItemIdForMovieId(movieId);
        cartItemRepository.deleteById(itemId);
    }

    private CartItemId prepareCartItemIdForMovieId(long movieId) {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return CartItemId.builder()
                .userId(userDetails.getId())
                .movieId(movieId)
                .build();
    }

    private CartItem getCartItemById(CartItemId id) {
        return cartItemRepository.findById(id).orElseThrow(() -> new CartItemNotFoundException(id.getMovieId()));
    }
}
