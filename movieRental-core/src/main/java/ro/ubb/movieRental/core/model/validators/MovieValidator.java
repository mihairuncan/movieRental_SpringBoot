package ro.ubb.movieRental.core.model.validators;


import ro.ubb.movieRental.core.model.Movie;
import ro.ubb.movieRental.core.model.exceptions.ValidatorException;

import java.util.Calendar;

public class MovieValidator implements Validator<Movie> {

    @Override
    public void validate(Movie movie) throws ValidatorException {
        if (movie.getName().equals("") || movie.getGenre().equals("")){
            throw new ValidatorException("Can't enter movie with empty fields");
        }

        if (movie.getYear() > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new ValidatorException("Year must not be greater than current year!");
        }
        if(movie.getYear()<=0){
            throw new ValidatorException("Year must be greater than 0!!!");
        }

        if(movie.getRentalPrice()<=0){
            throw new ValidatorException("Rental price can't be less than 1");
        }
    }

}
