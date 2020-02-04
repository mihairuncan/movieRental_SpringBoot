package ro.ubb.movieRental.web.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class ClientDto extends BaseDto {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String cnp;
}
