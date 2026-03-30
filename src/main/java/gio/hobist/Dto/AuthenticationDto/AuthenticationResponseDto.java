package gio.hobist.Dto.AuthenticationDto;

import java.util.UUID;

public record AuthenticationResponseDto(
        UUID id,
        String token
) {
}
