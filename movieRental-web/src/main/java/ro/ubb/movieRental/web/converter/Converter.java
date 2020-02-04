package ro.ubb.movieRental.web.converter;

import ro.ubb.movieRental.core.model.BaseEntity;
import ro.ubb.movieRental.web.dto.BaseDto;


public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

