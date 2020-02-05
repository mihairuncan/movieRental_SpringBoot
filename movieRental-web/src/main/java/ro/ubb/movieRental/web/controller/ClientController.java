package ro.ubb.movieRental.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.movieRental.core.model.Client;
import ro.ubb.movieRental.core.service.ClientService;
import ro.ubb.movieRental.web.converter.ClientConverter;
import ro.ubb.movieRental.web.dto.ClientDto;
import ro.ubb.movieRental.web.dto.ClientsDto;

import java.util.Set;

@RestController
public class ClientController {
    public static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    Set<ClientDto> getClients() {
        LOGGER.trace("getClients --- method entered!!!!!!!!!!!!!!!!!!!!!!!");

        Set<Client> clients = clientService.getAllClients();
        ClientsDto result = new ClientsDto((Set<ClientDto>) clientConverter.convertModelsToDtos(clients));

        LOGGER.trace("getClients: result={}",result);

        return result.getClients();
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    ClientDto saveClient(@RequestBody ClientDto clientDto) {
        LOGGER.trace("saveClient --- method entered");

        Client savedClient = clientService.save(
                clientConverter.convertDtoToModel(clientDto));
        ClientDto result = clientConverter.convertModelToDto(savedClient);

        LOGGER.trace("saveClient --- method exit");

        return result;
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    ClientDto updateClient(@PathVariable Long id,
                           @RequestBody ClientDto clientDto) {
        LOGGER.trace("updateClient --- method entered");

        Client client = clientService.update(id, clientConverter.convertDtoToModel(clientDto));

        LOGGER.trace("updateClient --- method exit");

        return clientConverter.convertModelToDto(client);
    }

    @RequestMapping(value = "clients/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id) {
        LOGGER.trace("deleteClient --- method entered");

        clientService.delete(id);

        LOGGER.trace("deleteClient --- method exit");

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
