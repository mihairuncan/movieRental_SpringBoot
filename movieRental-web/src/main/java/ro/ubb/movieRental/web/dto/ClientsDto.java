package ro.ubb.movieRental.web.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ClientsDto {
    private Set<ClientDto> clients;
}
