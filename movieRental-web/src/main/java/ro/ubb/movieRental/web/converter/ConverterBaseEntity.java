package ro.ubb.movieRental.web.converter;


import ro.ubb.movieRental.core.model.BaseEntity;
import ro.ubb.movieRental.web.dto.BaseDto;

/**
 * Created by radu.
 */

public interface ConverterBaseEntity<Model extends BaseEntity<Long>, Dto extends BaseDto>
        extends Converter<Model, Dto> {

}

