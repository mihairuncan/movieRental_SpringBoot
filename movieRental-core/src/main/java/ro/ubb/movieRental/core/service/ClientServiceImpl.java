package ro.ubb.movieRental.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.movieRental.core.model.Client;
import ro.ubb.movieRental.core.model.exceptions.ValidatorException;
import ro.ubb.movieRental.core.repository.ClientRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements ClientService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client findOne(Long id) {
        LOGGER.trace("findOne --- method entered");

        Client result = clientRepository.findById(id).get();

        LOGGER.trace("findById: result={}", result);

        return result;
    }

    @Override
    public Set<Client> getAllClients() {
        LOGGER.trace("getAllClients --- method entered");

        Iterable<Client> clients = clientRepository.findAll();

        LOGGER.trace("getAllClients: result={}", clients);

        return StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public Client save(Client client) {
        LOGGER.trace("save --- method entered");
        try {
            clientValidator.validate(client);
            LOGGER.trace("save --- client is valid");

            return clientRepository.save(client);

        } catch (ValidatorException ve) {
            ve.printStackTrace();
        }
        LOGGER.trace("save --- client is invalid");
        return null;
    }

    @Override
    @Transactional
    public Client update(Long id, Client client) {
        LOGGER.trace("update --- method entered");

        try {
            clientValidator.validate(client);
            LOGGER.trace("update --- client is valid");

            Client update = clientRepository.findById(id).get();
            update.setFirstName(client.getFirstName());
            update.setLastName(client.getLastName());
            update.setDateOfBirth(client.getDateOfBirth());
            update.setCnp(client.getCnp());

            LOGGER.trace("update: result={}", update);
            return update;
        } catch (ValidatorException ve) {
            ve.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        LOGGER.trace("delete --- method entered");

        clientRepository.deleteById(id);

        LOGGER.trace("delete --- method exit");
    }

}
