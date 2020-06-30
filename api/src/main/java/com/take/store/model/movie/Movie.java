package com.take.store.model.movie;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @Column(updatable = false)
    private long id;
    @Column(nullable = false)
    private String title;
    private Category category;
    private String year;
    private String cast;
    private String director;
    private String story;
    private double price;
}
