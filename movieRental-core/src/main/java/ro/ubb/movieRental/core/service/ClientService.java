package ro.ubb.movieRental.core.service;


import ro.ubb.movieRental.core.model.Client;
import ro.ubb.movieRental.core.model.validators.ClientValidator;

import java.util.Set;

public interface ClientService {
    ClientValidator clientValidator = new ClientValidator();

    Client findOne(Long id);

    Set<Client> getAllClients();

    Client save(Client client);

    Client update(Long id, Client client);

    void delete(Long id);


}
