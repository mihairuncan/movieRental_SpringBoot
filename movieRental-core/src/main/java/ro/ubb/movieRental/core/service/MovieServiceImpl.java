package ro.ubb.movieRental.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.movieRental.core.model.BaseEntity;
import ro.ubb.movieRental.core.model.Client;
import ro.ubb.movieRental.core.model.Movie;
import ro.ubb.movieRental.core.model.MovieClient;
import ro.ubb.movieRental.core.model.exceptions.ValidatorException;
import ro.ubb.movieRental.core.repository.ClientRepository;
import ro.ubb.movieRental.core.repository.MovieRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieServiceImpl implements MovieService {
    public static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ClientRepository clientRepository;

    /**
     * Find the entity with the given {@code id}.
     *
     * @param id must not be null
     * @return a Movie with the given id
     */
    @Override
    public Movie findOne(Long id) {
        LOGGER.trace("findOne --- method entered");

        Movie result = movieRepository.findById(id).get();

        LOGGER.trace("findById: result={}", result);

        return result;
    }

    /**
     * @return all Movies
     */
    @Override
    public Set<Movie> getAllMovies() {
        LOGGER.trace("getAllMovies --- method entered");

        Iterable<Movie> movies = movieRepository.findAll();

        LOGGER.trace("getAllMovies: result={}", movies);

        return StreamSupport.stream(movies.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Saves the given Movie
     *
     * @param movie must not be null
     * @return null if the Movie was saved otherwise (e.g. id already exists) returns the Movie.
     */
    @Override
    public Movie save(Movie movie) {
        LOGGER.trace("save --- method entered");

        try {
            movieValidator.validate(movie);
            LOGGER.trace("save --- movie is valid");

            return movieRepository.save(movie);

        } catch (ValidatorException ve) {
            ve.printStackTrace();
        }
        LOGGER.trace("save --- invalid movie");

        return null;

    }

    @Override
    @Transactional
    public Movie update(Long id,String name,String genre,Integer year,Integer rentalPrice,Set<Long> clients) {
        LOGGER.trace("update --- method entered");

        try {
//            movieValidator.validate(movie);
//            LOGGER.trace("update --- movie is valid");

            Movie update = movieRepository.findById(id).get();
            update.setName(name);
            update.setGenre(genre);
            update.setYear(year);
            update.setRentalPrice(rentalPrice);

            update.getClients().stream()
                    .map(BaseEntity::getId)
                    .forEach(clients::remove);
            List<Client> clientList =
                    clientRepository.findAllById(clients);
            clientList.forEach(update::addClient);
            LOGGER.trace("update: result={}", update);

            return update;

        } catch (ValidatorException ve) {
            ve.printStackTrace();
            LOGGER.error("movie update error", ve);
        }

        return null;
    }

    /**
     * Deletes the movie with given id
     *
     * @param id must not be null
     * @return null if there is no Movie with the given id, otherwise the removed Movie.
     */
    @Override
    public void delete(Long id) {
        LOGGER.trace("delete --- method entered");

        movieRepository.deleteById(id);

        LOGGER.trace("delete --- method exit");

    }

    @Override
    @Transactional
    public Movie updateMovieRents(Long movieId, Map<Long, Date> rents) {

        LOGGER.trace("updateMovieRents: movieId={},rents={}",movieId,rents);

        Movie movie = movieRepository.getOne(movieId);

        movie.getMovieClients().forEach(
                movieClient -> movieClient.setPickUpDate(rents.get(movieClient.getClient().getId())));

        return movie;
    }

    @Override
    @Transactional
    public Movie addMovieRent(Long movieId, MovieClient movieClient) {
        Movie movie = movieRepository.getOne(movieId);
        movie.getMovieClients().add(movieClient);

        return movie;
    }


}
