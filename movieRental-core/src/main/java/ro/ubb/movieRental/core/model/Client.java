package ro.ubb.movieRental.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class Client extends BaseEntity<Long> implements Serializable {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "cnp", nullable = false, length = 13)
    private String cnp;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<MovieClient> movieClients = new HashSet<>();

    public Set<Movie> getMovies() {
        return Collections.unmodifiableSet(
                movieClients.stream()
                        .map(MovieClient::getMovie)
                        .collect(Collectors.toSet()));
    }

    public void addMovie(Movie movie) {
        MovieClient movieClient = new MovieClient();
        movieClient.setMovie(movie);
        movieClient.setClient(this);
        movieClients.add(movieClient);
    }

    public void addPickUpDate(Movie movie, Date pickUpDate) {
        MovieClient movieClient = new MovieClient();
        movieClient.setMovie(movie);
        movieClient.setPickUpDate(pickUpDate);
        movieClient.setClient(this);
        movieClients.add(movieClient);
    }
}
