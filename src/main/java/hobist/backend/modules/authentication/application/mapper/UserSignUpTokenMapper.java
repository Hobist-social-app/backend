package hobist.backend.modules.authentication.application.mapper;

import hobist.backend.modules.authentication.infrastructure.security.dtos.DataForTokenDto;
import hobist.backend.modules.users.application.dtos.UserSignUpServiceResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface UserSignUpTokenMapper {

    @Mapping(target = "extraClaims", source = "dto", qualifiedByName = "toExtraClaims")
    @Mapping(target = "nickname", source = "dto.nickname")
    DataForTokenDto toDataForTokenDto(UserSignUpServiceResponseDto dto);

    @Named("toExtraClaims")
    default Map<String, Object> toExtraClaims(UserSignUpServiceResponseDto dto) {
        return Map.of(
                "userId", dto.id().toString(),
                "identifier", dto.email(),
                "role", dto.role().toString()
        );
    }
}
