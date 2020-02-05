package ro.ubb.movieRental.web.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class MovieDto extends BaseDto {
    private String name;
    private String genre;
    private Integer year;
    private Integer rentalPrice;
    private Set<Long> clients;
}
