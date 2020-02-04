package ro.ubb.movieRental.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class RentDto extends BaseDto {
    private long movieId;
    private long clientId;
    private String pickUpDate;
}