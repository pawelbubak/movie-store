package com.take.store.service;

import com.take.store.model.exception.MovieNotFoundException;
import com.take.store.model.movie.Category;
import com.take.store.model.movie.Movie;
import com.take.store.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public Page<Movie> getAll(Pageable pageable, String category) {
        if (category != null) {
            return movieRepository.findAllByCategory(pageable, Category.forValue(category));
        } else {
            return movieRepository.findAll(pageable);
        }
    }

    public Movie getById(long id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie update(long id, Movie movie) {
        getById(id);
        return save(movie);
    }

    public void deleteById(long id) {
        movieRepository.deleteById(id);
    }
}
