package com.take.store.repository;

import com.take.store.model.movie.Category;
import com.take.store.model.movie.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    public Page<Movie> findAllByCategory(Pageable pageable, Category category);
}
