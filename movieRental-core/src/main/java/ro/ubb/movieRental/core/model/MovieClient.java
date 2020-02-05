package ro.ubb.movieRental.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "movie_client")
@IdClass(MovieClientPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class MovieClient implements Serializable {

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;


    @Column(name = "pick_up_date")
    private Date pickUpDate;

    @Override
    public String toString() {
        return "Rent{" +
                "client=" + client.getId() +
                ", movie=" + movie.getId() +
                ", pickUpDate=" + pickUpDate +
                '}';
    }
}
