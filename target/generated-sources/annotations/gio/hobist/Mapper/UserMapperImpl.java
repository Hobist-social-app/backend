package gio.hobist.Mapper;

import gio.hobist.Dto.SignUp.SignUpResponseDto;
import gio.hobist.Entity.User;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-29T17:03:33+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public SignUpResponseDto toAuthenticationSignUpResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UUID id = null;
        String name = null;
        String email = null;

        id = user.getId();
        name = user.getName();
        email = user.getEmail();

        SignUpResponseDto signUpResponseDto = new SignUpResponseDto( id, name, email );

        return signUpResponseDto;
    }
}
