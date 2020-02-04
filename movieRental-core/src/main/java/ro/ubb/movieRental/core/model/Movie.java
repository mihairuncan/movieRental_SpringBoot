package ro.ubb.movieRental.core.model;

import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class Movie extends BaseEntity<Long> implements Serializable {
    private String name;
    private String genre;
    private int year;
    private int rentalPrice;
}
