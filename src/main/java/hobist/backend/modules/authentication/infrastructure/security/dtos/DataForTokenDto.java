package hobist.backend.modules.authentication.infrastructure.security.dtos;

import java.util.Map;

public record DataForTokenDto(
        Map<String,Object> extraClaims
        , String nickname
) {
}
