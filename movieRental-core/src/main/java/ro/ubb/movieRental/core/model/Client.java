package ro.ubb.movieRental.core.model;

import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class Client extends BaseEntity<Long> implements Serializable {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String cnp;
}
