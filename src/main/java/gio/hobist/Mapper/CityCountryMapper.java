package gio.hobist.Mapper;

import gio.hobist.Dto.CountryCityDto;
import gio.hobist.Entity.City;
import gio.hobist.Entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface CityCountryMapper {


    @Mapping(source="id",target="id")
    @Mapping(source="name",target="name")
    CountryCityDto toCityDto(City city);

    @Mapping(source="id",target="id")
    @Mapping(source="name",target="name")
    CountryCityDto toCountryDto(Country country);
}
