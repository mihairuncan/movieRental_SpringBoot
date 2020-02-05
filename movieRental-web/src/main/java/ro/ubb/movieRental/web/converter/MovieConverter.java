package ro.ubb.movieRental.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.movieRental.core.model.BaseEntity;
import ro.ubb.movieRental.core.model.Movie;
import ro.ubb.movieRental.web.dto.MovieDto;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class MovieConverter extends AbstractConverterBaseEntityConverter<Movie, MovieDto> {
    @Override
    public Movie convertDtoToModel(MovieDto dto) {
        Movie movie = Movie.builder()
                .name(dto.getName())
                .genre(dto.getGenre())
                .year(dto.getYear())
                .rentalPrice(dto.getRentalPrice())
                .movieClients(new HashSet<>())
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
                .clients(movie.getClients().stream()
                        .map(BaseEntity::getId).collect(Collectors.toSet()))
                .build();
        dto.setId(movie.getId());
        return dto;
    }
}
