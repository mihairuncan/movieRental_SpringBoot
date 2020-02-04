package ro.ubb.movieRental.web.dto;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class MoviesDto {
    private Set<MovieDto> movies;
}
