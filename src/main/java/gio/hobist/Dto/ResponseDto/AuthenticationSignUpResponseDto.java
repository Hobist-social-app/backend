package gio.hobist.Dto.ResponseDto;

import java.util.UUID;

public record AuthenticationSignUpResponseDto(
        UUID id,
        String name,
        String email
)
{}
