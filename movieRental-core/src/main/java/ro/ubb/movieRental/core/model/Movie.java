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
public class Movie extends BaseEntity<Long> implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "year", nullable = false, length = 4)
    private Integer year;

    @Column(name = "rental_price", nullable = false)
    private Integer rentalPrice;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<MovieClient> movieClients = new HashSet<>();

    public Set<Client> getClients() {
        return Collections.unmodifiableSet(
                this.movieClients.stream()
                        .map(MovieClient::getClient)
                        .collect(Collectors.toSet()));
    }

    public void addClient(Client client){
        MovieClient movieClient = new MovieClient();
        movieClient.setClient(client);
        movieClient.setMovie(this);
        movieClients.add(movieClient);
    }

    public void addPickUpDate(Client client, Date pickUpDate){
        MovieClient movieClient = new MovieClient();
        movieClient.setClient(client);
        movieClient.setPickUpDate(pickUpDate);
        movieClient.setMovie(this);
        movieClients.add(movieClient);
    }
}
