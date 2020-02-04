package ro.ubb.movieRental.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.movieRental.core.model.Movie;
import ro.ubb.movieRental.core.service.MovieService;
import ro.ubb.movieRental.web.converter.MovieConverter;
import ro.ubb.movieRental.web.dto.MovieDto;
import ro.ubb.movieRental.web.dto.MoviesDto;

import java.util.Set;

@RestController
public class MovieController {
    public static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    MovieService movieService;

    @Autowired
    MovieConverter movieConverter;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    Set<MovieDto> getMovies() {
        LOGGER.trace("getMovies --- method entered");

        Set<Movie> movies = movieService.getAllMovies();
        MoviesDto result = new MoviesDto(movieConverter.convertModelsToDtos(movies));

        LOGGER.trace("getMovies: result={}", result);

        return result.getMovies();
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    MovieDto saveMovie(@RequestBody MovieDto movieDto) {
        LOGGER.trace("saveMovie --- method entered");

        Movie movieSaved = movieService.save(movieConverter.convertDtoToModel(movieDto));
        MovieDto result = movieConverter.convertModelToDto(movieSaved);

        LOGGER.trace("saveMovie --- method exit");

        return result;
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    MovieDto updateMovie(@PathVariable Long id,
                          @RequestBody MovieDto movieDto) {

        LOGGER.trace("updateMovie --- method entered");

        Movie movie = movieService.update(id,movieConverter.convertDtoToModel(movieDto));
        MovieDto result = movieConverter.convertModelToDto(movie);

        LOGGER.trace("updateMovie --- method exit");

        return result;
    }

    @RequestMapping(value = "/movies/{id}",method = RequestMethod.DELETE)
    ResponseEntity<?> deleteMovie(@PathVariable Long id){
        LOGGER.trace("deleteMovie ---  method entered");

        movieService.delete(id);

        LOGGER.trace("deleteMovie --- method exit");

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
