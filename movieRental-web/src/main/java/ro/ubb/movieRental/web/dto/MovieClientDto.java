package ro.ubb.movieRental.web.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MovieClientDto {
    private Long clientId;
    private Long movieId;
    private String clientName;
    private String pickUpDate;
}
