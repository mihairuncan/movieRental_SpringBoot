package ro.ubb.movieRental.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.movieRental.core.model.MovieClient;
import ro.ubb.movieRental.core.service.ClientService;
import ro.ubb.movieRental.core.service.MovieService;
import ro.ubb.movieRental.web.dto.MovieClientDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class MovieClientConverter
        extends AbstractConverter<MovieClient, MovieClientDto> {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ClientService clientService;

    @Override
    public MovieClient convertDtoToModel(MovieClientDto dto) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        try {
            return MovieClient.builder()
                    .client(clientService.findOne(dto.getClientId()))
                    .movie(movieService.findOne(dto.getMovieId()))
                    .pickUpDate(format.parse(dto.getPickUpDate()))
                    .build();
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return null;
    }


    @Override
    public MovieClientDto convertModelToDto(MovieClient movieClient) {
        return MovieClientDto.builder()
                .movieId(movieClient.getMovie().getId())
                .clientId(movieClient.getClient().getId())
                .clientName(movieClient.getClient().getFirstName() + " " + movieClient.getClient().getLastName())
                .pickUpDate(movieClient.getPickUpDate().toString())
                .build();
    }
}
