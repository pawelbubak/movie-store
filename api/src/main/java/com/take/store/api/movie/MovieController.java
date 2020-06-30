package com.take.store.api.movie;

import com.take.store.model.movie.Movie;
import com.take.store.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public Page<Movie> getMovies(Pageable pageable, @RequestParam(required = false) String category) {
        return movieService.getAll(pageable, category);
    }

    @GetMapping("{id}")
    public Movie getMovie(@PathVariable long id) {
        return movieService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie create(@RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Movie update(@PathVariable long id, @RequestBody Movie movie) {
        return movieService.update(id, movie);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        movieService.deleteById(id);
    }
}
