package ro.ubb.movieRental.web.dto;

import lombok.*;

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
