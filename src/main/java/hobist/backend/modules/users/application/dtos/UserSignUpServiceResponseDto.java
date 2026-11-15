package hobist.backend.modules.users.application.dtos;

import hobist.backend.modules.users.domain.user.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.UUID;

public record UserSignUpServiceResponseDto(
        @NotNull UUID id
        ,@NotNull String nickname
        ,@NotNull String email
        ,@NotNull String role) {
}
