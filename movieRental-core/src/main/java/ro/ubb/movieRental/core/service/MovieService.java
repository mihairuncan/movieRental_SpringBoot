package ro.ubb.movieRental.core.service;


import ro.ubb.movieRental.core.model.Movie;
import ro.ubb.movieRental.core.model.MovieClient;
import ro.ubb.movieRental.core.model.validators.MovieValidator;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface MovieService {
    MovieValidator movieValidator = new MovieValidator();

    Movie findOne(Long id);

    Set<Movie> getAllMovies();

    Movie save(Movie movie);

    Movie update(Long movieId, String name,String genre,Integer year,Integer rentalPrice,Set<Long> clients);

    void delete(Long id);

    Movie updateMovieRents(Long movieId, Map<Long, Date> rents);

    Movie addMovieRent(Long movieId, MovieClient movieClient);
}
