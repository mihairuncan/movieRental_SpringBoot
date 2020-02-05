package ro.ubb.movieRental.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.movieRental.core.model.Movie;
import ro.ubb.movieRental.core.model.MovieClient;
import ro.ubb.movieRental.core.service.MovieService;
import ro.ubb.movieRental.web.converter.MovieClientConverter;
import ro.ubb.movieRental.web.dto.MovieClientDto;

import java.util.*;

@RestController
public class RentsController {
    private static final Logger log = LoggerFactory.getLogger(RentsController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieClientConverter movieClientConverter;

    @RequestMapping(value = "/rents/{movieId}", method = RequestMethod.GET)
    public Set<MovieClientDto> getMovieClients(@PathVariable final Long movieId) {
        log.trace("getMovieClients: movieId={}", movieId);

        Movie movie = movieService.findOne(movieId);

        Set<MovieClient> movieClients = movie.getMovieClients();

        Set<MovieClientDto> result = new HashSet<>(movieClientConverter
                .convertModelsToDtos(movieClients));

        log.trace("getMovieClients: result{}", result);

        return result;
    }

    @RequestMapping(value = "/rents/{movieId}", method = RequestMethod.PUT)
    public Set<MovieClientDto> updateMovieClients(
            @PathVariable final Long movieId,
            @RequestBody final Set<MovieClientDto> movieClientDtos) {
        log.trace("updateMovieClients: movieId={}, movieClientDtos",
                movieId, movieClientDtos);

        Map<Long, Date> rents = new HashMap<>();
        movieClientDtos.forEach(movieClientDto -> {
            MovieClient movieClient = movieClientConverter.convertDtoToModel(movieClientDto);
            rents.put(movieClient.getClient().getId(), movieClient.getPickUpDate());
        });

        Movie movie = movieService.updateMovieRents(movieId, rents);
        Set<MovieClientDto> result = new HashSet<>(movieClientConverter.convertModelsToDtos(movie.getMovieClients()));

        log.trace("getMovieClients: result={}", result);

        return result;
    }

    @RequestMapping(path = "/rents/{movieId}", method = RequestMethod.POST)
    public MovieClientDto addMovieClient(@PathVariable Long movieId,
                                         @RequestBody MovieClientDto movieClientDto) {

        movieService.addMovieRent(movieId, movieClientConverter.convertDtoToModel(movieClientDto));

        return movieClientDto;
    }

}
