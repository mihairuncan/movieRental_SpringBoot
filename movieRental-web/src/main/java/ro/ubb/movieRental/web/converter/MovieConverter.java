package ro.ubb.movieRental.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.movieRental.core.model.Movie;
import ro.ubb.movieRental.web.dto.MovieDto;

@Component
public class MovieConverter extends BaseConverter<Movie, MovieDto> {
    @Override
    public Movie convertDtoToModel(MovieDto dto) {
        Movie movie = Movie.builder()
                .name(dto.getName())
                .genre(dto.getGenre())
                .year(dto.getYear())
                .rentalPrice(dto.getRentalPrice())
                .build();
        movie.setId(dto.getId());
        return movie;
    }

    @Override
    public MovieDto convertModelToDto(Movie movie) {
        MovieDto dto = MovieDto.builder()
                .name(movie.getName())
                .genre(movie.getGenre())
                .year(movie.getYear())
                .rentalPrice(movie.getRentalPrice())
                .build();
        dto.setId(movie.getId());
        return dto;
    }
}
