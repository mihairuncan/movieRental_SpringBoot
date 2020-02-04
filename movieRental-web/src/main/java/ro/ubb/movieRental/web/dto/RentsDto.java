package ro.ubb.movieRental.web.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class RentsDto {
    private Set<RentDto> rents;
}
