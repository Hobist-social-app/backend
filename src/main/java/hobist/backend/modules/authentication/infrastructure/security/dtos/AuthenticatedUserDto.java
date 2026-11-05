package hobist.backend.modules.authentication.infrastructure.security.dtos;

import java.util.UUID;

public record AuthenticatedUserDto(
        UUID id,
        String email) {
}
