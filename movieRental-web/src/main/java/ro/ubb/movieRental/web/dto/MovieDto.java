package ro.ubb.movieRental.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class MovieDto extends BaseDto {
    private String name;
    private String genre;
    private int year;
    private int rentalPrice;
}
