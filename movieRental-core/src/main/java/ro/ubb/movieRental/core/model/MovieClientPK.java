package ro.ubb.movieRental.core.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class MovieClientPK implements Serializable {
    private Client client;
    private Movie movie;
}
