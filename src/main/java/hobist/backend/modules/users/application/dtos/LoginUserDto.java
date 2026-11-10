package hobist.backend.modules.users.application.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record LoginUserDto(
          @NotBlank UUID id
        , @NotBlank String nickname
        , @NotBlank String password
        , @NotBlank String email
        , @NotBlank String role) {
}
