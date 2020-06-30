package com.take.store.model.cart;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CartItemId implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "movie_id")
    private Long movieId;
}
