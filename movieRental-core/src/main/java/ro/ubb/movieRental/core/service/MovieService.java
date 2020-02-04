package ro.ubb.movieRental.core.service;


import ro.ubb.movieRental.core.model.Movie;
import ro.ubb.movieRental.core.model.validators.MovieValidator;

import java.util.Set;

public interface MovieService {
    MovieValidator movieValidator = new MovieValidator();

    Movie findOne(Long id);

    Set<Movie> getAllMovies();

    Movie save(Movie movie);

    Movie update(Long id, Movie movie);

    void delete(Long id);
}
