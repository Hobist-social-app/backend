package gio.hobist.Mapper;

import gio.hobist.Dto.CountryCityDto;
import gio.hobist.Entity.City;
import gio.hobist.Entity.Country;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-29T17:03:33+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class CityCountryMapperImpl implements CityCountryMapper {

    @Override
    public CountryCityDto toCityDto(City city) {
        if ( city == null ) {
            return null;
        }

        Integer id = null;
        String name = null;

        id = city.getId();
        name = city.getName();

        CountryCityDto countryCityDto = new CountryCityDto( id, name );

        return countryCityDto;
    }

    @Override
    public CountryCityDto toCountryDto(Country country) {
        if ( country == null ) {
            return null;
        }

        Integer id = null;
        String name = null;

        id = country.getId();
        name = country.getName();

        CountryCityDto countryCityDto = new CountryCityDto( id, name );

        return countryCityDto;
    }
}
