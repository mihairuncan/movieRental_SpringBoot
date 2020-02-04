package ro.ubb.movieRental.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.movieRental.core.model.Client;
import ro.ubb.movieRental.web.dto.ClientDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto>{
    @Override
    public Client convertDtoToModel(ClientDto dto) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Client client = Client.builder()
                    .firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .dateOfBirth(format.parse(dto.getDateOfBirth()))
                    .cnp(dto.getCnp())
                    .build();
            client.setId(dto.getId());
            return client;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto dto = ClientDto.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .dateOfBirth(client.getDateOfBirth().toString())
                .cnp(client.getCnp())
                .build();
        dto.setId(client.getId());
        return dto;
    }
}
