package ro.ubb.movieRental.core.model.validators;


import ro.ubb.movieRental.core.model.Client;
import ro.ubb.movieRental.core.model.exceptions.ValidatorException;

import java.text.SimpleDateFormat;

public class ClientValidator implements Validator<Client> {
    @Override
    public void validate(Client client) throws ValidatorException {

        try {
            Long.parseLong(client.getCnp());
        } catch (NumberFormatException ne) {
            throw new ValidatorException("Invalid CNP!!!");
        }

        if (client.getCnp().length() != 13) {
            throw new ValidatorException("CNP must have 13 characters!!!");
        }

        if (client.getFirstName().equals("") || client.getLastName().equals("")){
            throw new ValidatorException("Can't enter client with empty fields");
        }

    }
}
