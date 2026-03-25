package gio.hobist.Mapper;

import gio.hobist.Dto.SignUp.SignUpResponseDto;
import gio.hobist.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source="id",target="id")
    @Mapping(source="name",target="name")
    @Mapping(source="email",target="email")
    SignUpResponseDto toAuthenticationSignUpResponseDto(User user);
}
