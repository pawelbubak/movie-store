package com.take.store.repository;

import com.take.store.model.cart.CartItem;
import com.take.store.model.cart.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
    public List<CartItem> findAllByIdUserId(long userId);
}
