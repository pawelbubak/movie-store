package com.take.store.model.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.take.store.model.movie.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_items")
public class CartItem {
    @JsonIgnore
    @EmbeddedId
    private CartItemId id;
    private long quantity;
    @Transient
    private double totalPrice;

    @ManyToOne
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id", updatable = false, insertable = false)
    private Movie movie;

    @PostLoad
    public void setTotalPrice() {
        totalPrice = movie.getPrice() * quantity;
    }
}
