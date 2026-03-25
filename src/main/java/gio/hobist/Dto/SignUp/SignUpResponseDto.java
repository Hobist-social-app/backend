package gio.hobist.Dto.SignUp;

import java.util.UUID;

public record SignUpResponseDto(
        UUID id,
        String name,
        String email
)
{}
