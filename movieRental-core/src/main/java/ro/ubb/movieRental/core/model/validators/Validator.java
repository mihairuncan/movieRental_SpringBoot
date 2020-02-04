package ro.ubb.movieRental.core.model.validators;


import ro.ubb.movieRental.core.model.BaseEntity;
import ro.ubb.movieRental.core.model.exceptions.ValidatorException;

public interface Validator<T extends BaseEntity> {
    void validate(T entity) throws ValidatorException;
}
